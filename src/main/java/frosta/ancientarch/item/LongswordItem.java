package frosta.ancientarch.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

import java.util.UUID;

public class LongswordItem extends SwordItem {
    private static final int BUFF_INTERVAL_TICKS = 20; // 1 second (20 ticks)
    private static final float BUFFED_ATTACK_DAMAGE = 9.5f; // example buffed value
    private static final float NORMAL_ATTACK_DAMAGE = 7.5f; // example normal value
    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a19c2e44-2222-4a0d-aaaa-bb3d72a67890");


    private int tickCounter = 0;
    private float currentAttackDamage;

    public LongswordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.currentAttackDamage = attackDamage;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player && !world.isClient) {
            tickCounter++;
            if (tickCounter >= BUFF_INTERVAL_TICKS) {
                tickCounter = 0;
                if (hasFullSet(player)) {
                    currentAttackDamage = BUFFED_ATTACK_DAMAGE;
                } else {
                    currentAttackDamage = NORMAL_ATTACK_DAMAGE;
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create(super.getAttributeModifiers(slot));
        if (slot == EquipmentSlot.MAINHAND) {
            modifiers.removeAll(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(
                            ATTACK_DAMAGE_MODIFIER_ID,
                            "Weapon modifier",
                            currentAttackDamage,
                            EntityAttributeModifier.Operation.ADDITION
                    )
            );
            modifiers.putAll(super.getAttributeModifiers(slot));
            modifiers.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", 0.75, EntityAttributeModifier.Operation.ADDITION));
            modifiers.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_ID, "Weapon modifier", 0.75, EntityAttributeModifier.Operation.ADDITION));
        }
        return modifiers;
    }

    public static boolean hasFullSet(PlayerEntity player) {
        return player.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof AncientArmorItem
                && player.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof AncientArmorItem
                && player.getEquippedStack(EquipmentSlot.LEGS).getItem() instanceof AncientArmorItem
                && player.getEquippedStack(EquipmentSlot.FEET).getItem() instanceof AncientArmorItem;
    }
}
