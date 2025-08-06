package frosta.ancientarch.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class SwordofSanctuaryItem extends SwordItem {
    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a19c2e44-2222-4a0d-aaaa-bb3d72a67890");

    public SwordofSanctuaryItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create(super.getAttributeModifiers(slot));
        if (slot == EquipmentSlot.MAINHAND) {
            modifiers.putAll(super.getAttributeModifiers(slot));

            modifiers.put(ReachEntityAttributes.ATTACK_RANGE,
                    new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", 0.75, EntityAttributeModifier.Operation.ADDITION));
            modifiers.put(ReachEntityAttributes.REACH,
                    new EntityAttributeModifier(REACH_MODIFIER_ID, "Weapon modifier", 0.75, EntityAttributeModifier.Operation.ADDITION));

           }
        return modifiers;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String[] strings = Text.translatable("item.ancient-arch.sword_of_sanctuary.lore").getString().split("\n");
        for (String s : strings) {
            tooltip.add(Text.literal(s).formatted(Formatting.DARK_AQUA, Formatting.ITALIC, Formatting.OBFUSCATED));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }
}