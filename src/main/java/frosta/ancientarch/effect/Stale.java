package frosta.ancientarch.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.UUID;

public class Stale extends StatusEffect {

    private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("a709e8c6-d7b2-4d7f-bca4-7c2d3b2e7c1e");

    public Stale() {
        super(StatusEffectCategory.HARMFUL, 0xAAAAAA); // grey-ish color

        this.addAttributeModifier(
                EntityAttributes.GENERIC_MOVEMENT_SPEED,
                SPEED_MODIFIER_UUID.toString(),
                -0.5,
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }
}
