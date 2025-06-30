package frosta.ancientarch;


import frosta.ancientarch.item.ModItems;
import nazario.liby.api.LibyModelLoaderEntrypoint;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;

public class AncientArchClient implements ClientModInitializer, LibyModelLoaderEntrypoint {

    @Override
    public void onInitializeClient() {

    }

    @Override
    public void onLibyModelLoaderInitialize() {
        this.liby$registerSpecialItemModel(
                ModItems.ANCIENT_LONGSWORD,
                new ModelIdentifier(AncientArch.MOD_ID, "ancient_longsword_inventory", "inventory"),
                ModelTransformationMode.FIRST_PERSON_LEFT_HAND, ModelTransformationMode.FIRST_PERSON_RIGHT_HAND, ModelTransformationMode.THIRD_PERSON_LEFT_HAND, ModelTransformationMode.THIRD_PERSON_RIGHT_HAND, ModelTransformationMode.FIXED
        );
        this.liby$registerSpecialItemModel(
                ModItems.ANCIENT_GREATAXE,
                new ModelIdentifier(AncientArch.MOD_ID, "ancient_greataxe_inventory", "inventory"),
                ModelTransformationMode.FIRST_PERSON_LEFT_HAND, ModelTransformationMode.FIRST_PERSON_RIGHT_HAND, ModelTransformationMode.THIRD_PERSON_LEFT_HAND, ModelTransformationMode.THIRD_PERSON_RIGHT_HAND, ModelTransformationMode.FIXED
        );

    }
}
