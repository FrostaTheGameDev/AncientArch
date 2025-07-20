package frosta.ancientarch.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class LongswordItem extends SwordItem {
    private static final int BUFF_INTERVAL_TICKS = 20; // 1 second (20 ticks)
    private static final float BUFFED_ATTACK_DAMAGE = 9.5f; // example buffed value
    private static final float NORMAL_ATTACK_DAMAGE = 7.5f; // example normal value

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
