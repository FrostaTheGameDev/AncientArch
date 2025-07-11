package frosta.ancientarch.client;


import frosta.ancientarch.AncientArch;
import frosta.ancientarch.item.ModItems;
import nazario.liby.api.client.entrypoint.LibyAssetLoadingEntrypoint;
import nazario.liby.api.client.entrypoint.LibyAssetRegistryAccess;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.util.ModelIdentifier;

public class AncientArchClient implements ClientModInitializer, LibyAssetLoadingEntrypoint {

    @Override
    public void onInitializeClient() {

    }

    @Override
    public void onLibyAssetLoading(LibyAssetRegistryAccess libyAssetRegistryAccess) {
        libyAssetRegistryAccess.addItemPredicateModel(ModItems.ANCIENT_GREATAXE, new ModelIdentifier(AncientArch.MOD_ID, "ancient_greataxe_inventory", "inventory"), (mode) -> switch(mode) {
            case GUI, GROUND -> true;
            default -> false;
        });
        libyAssetRegistryAccess.addItemPredicateModel(ModItems.ANCIENT_LONGSWORD, new ModelIdentifier(AncientArch.MOD_ID, "ancient_longsword_inventory", "inventory"), (mode) -> switch(mode) {
            case GUI, GROUND -> true;
            default -> false;
        });
    }
}