package frosta.ancientarch.item;

import frosta.ancientarch.AncientArch;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.IdentityHashMap;

public class ModItems {
    public static final Item Silica = registerItem("silica", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(Silica);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AncientArch.MOD_ID, name), item);
    }

    public static void registerModItems() {
        AncientArch.LOGGER.info("Registering Mod Items for " + AncientArch.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
