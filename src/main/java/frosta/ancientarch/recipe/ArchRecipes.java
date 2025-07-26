package frosta.ancientarch.recipe;

import frosta.ancientarch.AncientArch;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.registry.Registry.*;

public class ArchRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(AncientArch.MOD_ID, KilnRecipe.Serializer.ID),
                KilnRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(AncientArch.MOD_ID, KilnRecipe.Type.ID),
                KilnRecipe.Type.INSTANCE);
    }
}