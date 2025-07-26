package frosta.ancientarch.block.blockentity;

import frosta.ancientarch.recipe.KilnRecipe;
import frosta.ancientarch.screen.KilnBlockScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class KilnBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private static final int ANCIENT_MOULD_SLOT = 0;
    private static final int INGOT_SLOT = 1;
    private static final int FUEL_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;

    private int progress = 0;
    private int maxProgress = 172;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    private final PropertyDelegate propertyDelegate;

    public KilnBlockEntity(BlockPos pos, BlockState state) {
        super(ArchBlockEntitys.KILN_BLOCK_ENTITY, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> KilnBlockEntity.this.progress;
                    case 1 -> KilnBlockEntity.this.maxProgress;
                    case 2 -> KilnBlockEntity.this.fuelTime;
                    case 3 -> KilnBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> KilnBlockEntity.this.progress = value;
                    case 1 -> KilnBlockEntity.this.maxProgress = value;
                    case 2 -> KilnBlockEntity.this.fuelTime = value;
                    case 3 -> KilnBlockEntity.this.maxFuelTime = value;
                }
            }

            public int size() {
                return 4;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Kiln");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("kiln.progress", progress);
        nbt.putInt("kiln.fuelTime", fuelTime);
        nbt.putInt("kiln.maxFuelTime", maxFuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("kiln.progress");
        fuelTime = nbt.getInt("kiln.fuelTime");
        maxFuelTime = nbt.getInt("kiln.maxFuelTime");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new KilnBlockScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    public static void tick(World world, BlockPos pos, BlockState state, KilnBlockEntity entity) {
        if (world.isClient()) return;

        boolean wasLit = state.get(AbstractFurnaceBlock.LIT);
        boolean isLit = entity.isBurning();

        boolean hasRecipe = entity.hasRecipe();

        if (isLit) {
            entity.fuelTime--;
        }

        if (hasRecipe) {
            if (!isLit && entity.hasFuel()) {
                entity.consumeFuel();
                isLit = true;
            }

            if (isLit) {
                entity.progress++;
                if (entity.progress >= entity.maxProgress) {
                    entity.craftItem();
                    entity.resetProgress();
                }
            } else {
                entity.resetProgress();
            }
        } else {
            entity.resetProgress();
        }

        if (wasLit != isLit) {
            world.setBlockState(pos, state.with(AbstractFurnaceBlock.LIT, isLit), 3);
        }

        markDirty(world, pos, state);
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void consumeFuel() {
        ItemStack fuelStack = this.getStack(FUEL_SLOT);
        Integer fuelTime = FuelRegistry.INSTANCE.get(fuelStack.getItem());

        if (fuelTime != null) {
            this.maxFuelTime = this.fuelTime = fuelTime;
            fuelStack.decrement(1);
        }
    }

    private boolean isBurning() {
        return fuelTime > 0;
    }

    private boolean hasFuel() {
        ItemStack fuelStack = getStack(FUEL_SLOT);
        if (fuelStack.isEmpty()) return false;

        Integer fuelTime = FuelRegistry.INSTANCE.get(fuelStack.getItem());
        return fuelTime != null && fuelTime > 0;
    }

    private void craftItem() {
        Optional<KilnRecipe> match = getCurrentRecipe();
        if (match.isEmpty()) return;

        KilnRecipe recipe = match.get();
        DefaultedList<Ingredient> ingredients = recipe.getIngredients();

        getStack(ANCIENT_MOULD_SLOT).decrement(ingredients.get(0).getMatchingStacks()[0].getCount());
        getStack(INGOT_SLOT).decrement(ingredients.get(1).getMatchingStacks()[0].getCount());

        ItemStack result = recipe.getResult(null);
        ItemStack output = this.getStack(OUTPUT_SLOT);

        if (output.isEmpty()) {
            this.setStack(OUTPUT_SLOT, result.copy());
        } else {
            output.increment(result.getCount());
        }
    }

    private boolean hasRecipe() {
        Optional<KilnRecipe> match = getCurrentRecipe();
        if (match.isEmpty()) return false;

        KilnRecipe recipe = match.get();
        ItemStack result = recipe.getResult(null);

        return canInsertItemIntoOutputSlot(result.getItem()) &&
                canInsertAmountIntoOutputSlot(result);
    }

    private Optional<KilnRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(KilnRecipe.Type.INSTANCE, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        ItemStack output = this.getStack(OUTPUT_SLOT);
        return output.isEmpty() || output.getItem() == item;
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        ItemStack output = this.getStack(OUTPUT_SLOT);
        return output.getCount() + result.getCount() <= output.getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        ItemStack output = this.getStack(OUTPUT_SLOT);
        return output.isEmpty() || output.getCount() < output.getMaxCount();
    }
}