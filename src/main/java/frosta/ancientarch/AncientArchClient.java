package frosta.ancientarch;


import frosta.ancientarch.item.ModItems;
import nazario.liby.api.LibyModelLoaderEntrypoint;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;

public class AncientArchClient implements ClientModInitializer, LibyModelLoaderEntrypoint {

    @Override
    public void onInitializeClient() {

    }

    @Override
    public void onLibyModelLoaderInitialize() {
        this.liby$registerSpecialItemModel(
                ModItems.ANCIENT_LONGSWORD,
                new ModelIdentifier(AncientArch.MOD_ID, "YOURITEM_3D", "inventory"),
                ModelTransformation.mode.GUI, ModelTransformation.Mode.GROUND, ModelTransformation.Mode.FIXED
        );

    }
}
