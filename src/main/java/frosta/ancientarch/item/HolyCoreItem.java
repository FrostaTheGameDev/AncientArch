package frosta.ancientarch.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class HolyCoreItem extends Item {
    public HolyCoreItem(Settings settings) {
        super(settings);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            NbtCompound nbt = stack.getOrCreateNbt();
            if (!nbt.contains("BoundUUID")) {
                UUID uuid = user.getUuid();
                nbt.putUuid("BoundUUID", uuid);
                nbt.putString("BoundName", user.getName().getString());
                user.sendMessage(Text.literal("Soul bound..."), true);
            } else {
                UUID boundUUID = nbt.getUuid("BoundUUID");
                if (boundUUID.equals(user.getUuid())) {
                }
            }
        }

        return TypedActionResult.success(stack, world.isClient());
    }

    public static boolean isBoundTo(ItemStack stack, PlayerEntity player) {
        if (!stack.hasNbt()) {
            return false;
        } else {
            NbtCompound nbt = stack.getNbt();
            return nbt != null && nbt.contains("BoundUUID") && player.getUuid().equals(nbt.getUuid("BoundUUID"));
        }
    }

    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt() && stack.getNbt().contains("BoundName")) {
            String name = stack.getNbt().getString("BoundName");
            tooltip.add(Text.literal("Bound to: ").formatted(Formatting.GREEN).append(Text.literal(name).formatted(Formatting.GREEN)));
        } else {
            tooltip.add(Text.literal("Unbound").formatted(new Formatting[]{Formatting.GRAY, Formatting.ITALIC}));
        }

    }
}
