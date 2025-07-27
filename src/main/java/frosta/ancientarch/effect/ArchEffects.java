package frosta.ancientarch.effect;

import frosta.ancientarch.AncientArch;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArchEffects {
    public static final StatusEffect STALE = Registry.register(
            Registries.STATUS_EFFECT, new Identifier(AncientArch.MOD_ID, "stale"), new Stale());

    public static void registerModEffects() {

    }
}
