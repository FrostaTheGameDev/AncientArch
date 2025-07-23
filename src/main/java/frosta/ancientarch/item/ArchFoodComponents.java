package frosta.ancientarch.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ArchFoodComponents {
    public static final FoodComponent SILICA_GEL_PACKET = new FoodComponent.Builder().hunger(1).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 120), 100).build();

    public static final FoodComponent PINEAPPLE = new FoodComponent.Builder().hunger(3).saturationModifier(1).build();

    public static final FoodComponent FALSE_APPLE = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(1.2F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 3000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 3000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 2), 1.0F)
            .alwaysEdible()
            .build();
}