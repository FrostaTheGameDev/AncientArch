package frosta.ancientarch.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class AncientRecipeProvider extends FabricRecipeProvider {
    public AncientRecipeProvider(FabricDataOutput output) {
        super(output);
    }


    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        //region
        //endregion
    }

    protected static void offerAndCriterion(Consumer<RecipeJsonProvider> consumer, String criterionName, CriterionConditions conditions, CraftingRecipeJsonBuilder... builders) {
        for (CraftingRecipeJsonBuilder builder : builders) {
            builder.criterion(criterionName, conditions).offerTo(consumer);
        }
    }

    protected ShapedRecipeJsonBuilder createCrateRecipe(Ingredient input, ItemConvertible output, int count) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, count)
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .input('#', input);
    }

    protected ShapedRecipeJsonBuilder createChainRecipe(Ingredient ingotInput, Ingredient nuggetInput, ItemConvertible output, int count) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, count)
                .pattern("N")
                .pattern("I")
                .pattern("N")
                .input('I', ingotInput)
                .input('N', nuggetInput);
    }
}
