package frosta.ancientarch.client;


import frosta.ancientarch.AncientArch;
import frosta.ancientarch.block.ArchBlocks;
import frosta.ancientarch.entity.ArchEntities;
import frosta.ancientarch.item.ArchItems;
import frosta.ancientarch.screen.ArchScreenHandlers;
import frosta.ancientarch.screen.KilnBlockScreen;
import nazario.liby.api.client.entrypoint.LibyAssetLoadingEntrypoint;
import nazario.liby.api.client.entrypoint.LibyAssetRegistryAccess;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

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
        libyAssetRegistryAccess.addItemPredicateModel(ArchItems.THE_QUEENS_DECAPITATOR, new ModelIdentifier(AncientArch.MOD_ID, "the_queens_decapitator_inventory", "inventory"), (mode) -> switch(mode) {
            case GUI, GROUND -> true;
            default -> false;
        });
        libyAssetRegistryAccess.addItemPredicateModel(ArchItems.HOLY_STILL_SWORD, new ModelIdentifier(AncientArch.MOD_ID, "holy_still_sword_inventory", "inventory"), (mode) -> switch(mode) {
            case GUI, GROUND -> true;
            default -> false;
        });
        libyAssetRegistryAccess.addItemPredicateModel(ArchItems.PURIFIED_WINTER, new ModelIdentifier(AncientArch.MOD_ID, "purified_winter_inventory", "inventory"), (mode) -> switch(mode) {
            case GUI, GROUND -> true;
            default -> false;
        });
        libyAssetRegistryAccess.addItemPredicateModel(ArchItems.SWORD_OF_SANCTUARY, new ModelIdentifier(AncientArch.MOD_ID, "sword_of_sanctuary_inventory", "inventory"), (mode) -> switch(mode) {
            case GUI, GROUND -> true;
            default -> false;
        });

        BlockRenderLayerMap.INSTANCE.putBlock(ArchBlocks.PINEAPPLE_CROP, RenderLayer.getCutout());

        EntityRendererRegistry.register(ArchEntities.HOLY_HAND_GRENADE_PR, FlyingItemEntityRenderer::new);
    }
}