package frosta.ancientarch.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ArchFoodComponents {
    public static final FoodComponent SILICA_GEL_PACKET = new FoodComponent.Builder().hunger(1).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 120), 100).build();
    public static final FoodComponent PINEAPPLE = new FoodComponent.Builder().hunger(3).saturationModifier(1).build();
}
