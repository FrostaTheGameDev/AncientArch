package frosta.ancientarch.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import frosta.ancientarch.effect.ArchEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;

import java.util.Random;
import java.util.UUID;

public class StillSwordItem extends SwordItem {
    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a19c2e44-2222-4a0d-aaaa-bb3d72a67890");
    protected static final UUID SLOWNESS_MODIFIER_ID = UUID.fromString("b77eaa6a-8d3a-4655-b3aa-34e37b5249e5");
    private static final Random RANDOM = new Random();

    public StillSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
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

            modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                    new EntityAttributeModifier(SLOWNESS_MODIFIER_ID, "Still Sword Slowness", -0.25, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return modifiers;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 50% chance to apply "Stillness"
        if (!target.getWorld().isClient && RANDOM.nextFloat() < 0.5f) {
            target.addStatusEffect(new StatusEffectInstance(ArchEffects.STILLNESS, 20 * 10, 0));
        }
        return super.postHit(stack, target, attacker);
    }
}