package frosta.ancientarch.recipe;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class KilnRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public KilnRecipe(Identifier id, List<Ingredient> ingredients, ItemStack output) {
        this.id = id;
        this.output = output;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient) return false;
        for (int i = 0; i < recipeItems.size(); i++) {
            if (!recipeItems.get(i).test(inventory.getStack(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, net.minecraft.registry.DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(net.minecraft.registry.DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(recipeItems.size(), Ingredient.EMPTY);
        for (int i = 0; i < recipeItems.size(); i++) {
            list.set(i, recipeItems.get(i));
        }
        return list;
    }

    public ItemStack getResult(net.minecraft.registry.DynamicRegistryManager registryManager) {
        return output.copy();
    }

    public static class Type implements RecipeType<KilnRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "kiln";
    }

    public static class Serializer implements RecipeSerializer<KilnRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "kiln";

        @Override
        public KilnRecipe read(Identifier id, com.google.gson.JsonObject json) {
            var ingredientsJson = net.minecraft.util.JsonHelper.getArray(json, "ingredients");
            List<Ingredient> ingredients = new java.util.ArrayList<>();
            for (var element : ingredientsJson) {
                ingredients.add(Ingredient.fromJson(element));
            }

            var resultJson = net.minecraft.util.JsonHelper.getObject(json, "result");
            ItemStack output = ShapedRecipe.outputFromJson(resultJson);

            return new KilnRecipe(id, ingredients, output);
        }

        @Override
        public KilnRecipe read(Identifier id, net.minecraft.network.PacketByteBuf buf) {
            int ingredientCount = buf.readInt();
            List<Ingredient> ingredients = new java.util.ArrayList<>(ingredientCount);
            for (int i = 0; i < ingredientCount; i++) {
                ingredients.add(Ingredient.fromPacket(buf));
            }
            ItemStack output = buf.readItemStack();
            return new KilnRecipe(id, ingredients, output);
        }

        @Override
        public void write(net.minecraft.network.PacketByteBuf buf, KilnRecipe recipe) {
            buf.writeInt(recipe.recipeItems.size());
            for (Ingredient ingredient : recipe.recipeItems) {
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.output);
        }
    }
}