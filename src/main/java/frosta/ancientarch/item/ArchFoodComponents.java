package frosta.ancientarch.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ArchFoodComponents {

    public static final FoodComponent PINEAPPLE = new FoodComponent.Builder().hunger(3).saturationModifier(1).build();

    public static final FoodComponent REMNANT_BOTTLE = new FoodComponent.Builder()
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 1200, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 2), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0), 1.0F)
            .build();

}