package frosta.ancientarch.effect;

import frosta.ancientarch.AncientArch;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArchEffects {
    public static final StatusEffect STILLNESS = Registry.register(
            Registries.STATUS_EFFECT, new Identifier(AncientArch.MOD_ID, "stillness"), new Stillness());

    public static void registerModEffects() {

    }
}
