package frosta.ancientarch.client;


import frosta.ancientarch.AncientArch;
import frosta.ancientarch.block.ArchBlocks;
import frosta.ancientarch.item.ArchItems;
import frosta.ancientarch.screen.ArchScreenHandlers;
import frosta.ancientarch.screen.KilnBlockScreen;
import nazario.liby.api.client.entrypoint.LibyAssetLoadingEntrypoint;
import nazario.liby.api.client.entrypoint.LibyAssetRegistryAccess;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.ModelIdentifier;

public class AncientArchClient implements ClientModInitializer, LibyAssetLoadingEntrypoint {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ArchScreenHandlers.KILN_BLOCK_SCREEN_HANDLER, KilnBlockScreen::new);
    }

    @Override
    public void onLibyAssetLoading(LibyAssetRegistryAccess libyAssetRegistryAccess) {
        libyAssetRegistryAccess.addItemPredicateModel(ArchItems.ANCIENT_GREATAXE, new ModelIdentifier(AncientArch.MOD_ID, "ancient_greataxe_inventory", "inventory"), (mode) -> switch(mode) {
            case GUI, GROUND -> true;
            default -> false;
        });
        libyAssetRegistryAccess.addItemPredicateModel(ArchItems.ANCIENT_LONGSWORD, new ModelIdentifier(AncientArch.MOD_ID, "ancient_longsword_inventory", "inventory"), (mode) -> switch(mode) {
            case GUI, GROUND -> true;
            default -> false;
        });
        libyAssetRegistryAccess.addItemPredicateModel(ArchItems.HEAD_CHOPPER, new ModelIdentifier(AncientArch.MOD_ID, "head_chopper_inventory", "inventory"), (mode) -> switch(mode) {
            case GUI, GROUND -> true;
            default -> false;
        });
        libyAssetRegistryAccess.addItemPredicateModel(ArchItems.HOLY_STILL_SWORD, new ModelIdentifier(AncientArch.MOD_ID, "holy_still_sword_inventory", "inventory"), (mode) -> switch(mode) {
            case GUI, GROUND -> true;
            default -> false;
        });

        BlockRenderLayerMap.INSTANCE.putBlock(ArchBlocks.PINEAPPLE_CROP, RenderLayer.getCutout());
    }
}