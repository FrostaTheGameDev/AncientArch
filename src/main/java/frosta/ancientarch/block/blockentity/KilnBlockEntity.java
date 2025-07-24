package frosta.ancientarch.block.blockentity;

import frosta.ancientarch.item.ArchItems;
import frosta.ancientarch.screen.KilnBlockScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class KilnBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private static final int INPUT_SLOT = 0;              // Vial of Ink
    private static final int GOLDEN_APPLE_SLOT = 1;       // Golden Apple
    private static final int OUTPUT_SLOT = 2;             // Ink Dipped Apple

    private int progress = 0;
    private int maxProgress = 72;

    private final PropertyDelegate propertyDelegate;

    public KilnBlockEntity(BlockPos pos, BlockState state) {
        super(ArchBlockEntitys.KILN_BLOCK_ENTITY, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> KilnBlockEntity.this.progress;
                    case 1 -> KilnBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> KilnBlockEntity.this.progress = value;
                    case 1 -> KilnBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
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

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new KilnBlockScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("alchemist_stand.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("alchemist_stand.progress");
    }

    public static void tick(World world, BlockPos pos, BlockState state, KilnBlockEntity entity) {
        if (world.isClient()) return;

        if (entity.isOutputSlotEmptyOrReceivable()) {
            if (entity.hasRecipe()) {
                entity.increaseCraftProgress();
                markDirty(world, pos, state);

                if (entity.hasCraftingFinished()) {
                    entity.craftItem();
                    entity.resetProgress();
                }
            } else {
                entity.resetProgress();
            }
        } else {
            entity.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        this.removeStack(INPUT_SLOT, 1);
        this.removeStack(GOLDEN_APPLE_SLOT, 1);

        ItemStack result = new ItemStack(ArchItems.EMPTY_CORE);
        ItemStack currentOutput = getStack(OUTPUT_SLOT);

        if (currentOutput.isEmpty()) {
            this.setStack(OUTPUT_SLOT, result);
        } else {
            currentOutput.increment(result.getCount());
        }
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack vialOfInk = getStack(INPUT_SLOT);
        ItemStack goldenApple = getStack(GOLDEN_APPLE_SLOT);
        ItemStack result = new ItemStack(ArchItems.PINEAPPLE);

        boolean hasInk = vialOfInk.getItem() == ArchItems.PINEAPPLE_SEEDS;
        boolean hasGoldenApple = goldenApple.getItem() == Items.GOLDEN_APPLE;

        return hasInk && hasGoldenApple &&
                canInsertAmountIntoOutputSlot(result) &&
                canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        ItemStack output = getStack(OUTPUT_SLOT);
        return output.isEmpty() || output.getItem() == item;
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return getStack(OUTPUT_SLOT).isEmpty() ||
                getStack(OUTPUT_SLOT).getCount() < getStack(OUTPUT_SLOT).getMaxCount();
    }
}