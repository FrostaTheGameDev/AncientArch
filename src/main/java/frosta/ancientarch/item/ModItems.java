package frosta.ancientarch.item;

import frosta.ancientarch.AncientArch;
import frosta.ancientarch.item.custom.HammerAndChiselItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item SILICA_GEL_PACKET = registerItem("silica_gel_packet", new Item(new FabricItemSettings().food(ModFoodComponents.SILICA_GEL_PACKET)));
    public static final Item MOLDABLE_PORCELAIN = registerItem("moldable_porcelain", new Item(new FabricItemSettings()));
    public static final Item CLAY_SLIP = registerItem("clay_slip", new Item(new FabricItemSettings()));
    public static final Item SILICA = registerItem("silica", new Item(new FabricItemSettings()));
    public static final Item REMNANT_SHARD = registerItem("remnant_shard", new Item(new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item MOLTEN_REMNANT = registerItem("molten_remnant", new Item(new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item UNCONDITIONED_ANCIENT_MOULD = registerItem("unconditioned_ancient_mould", new Item(new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item CONDITIONED_ANCIENT_MOULD = registerItem("conditioned_ancient_mould", new Item(new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item CONDITIONED_ARMOR_MOULD = registerItem("conditioned_armor_mould", new Item(new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item CONDITIONED_WEAPON_MOULD = registerItem("conditioned_weapon_mould", new Item(new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item HAMMER_AND_CHISEL = registerItem("hammer_and_chisel", new HammerAndChiselItem(ToolMaterials.NETHERITE, new FabricItemSettings().maxCount(1)));
    public static final Item ANCIENT_INGOT = registerItem("ancient_ingot", new Item(new FabricItemSettings()));
    public static final Item UNREFINED_ANCIENT_AMALGAM = registerItem("unrefined_ancient_amalgam", new Item(new FabricItemSettings()));
    public static final Item PINEAPPLE = registerItem("pineapple", new Item(new FabricItemSettings().food(ModFoodComponents.PINEAPPLE)));

    public static final Item ANCIENT_GREATAXE = registerItem("ancient_greataxe", new AxeItem(ModToolMaterial.ANCIENT_INGOT, 9f,-3f, new FabricItemSettings()));
    public static final Item ANCIENT_LONGSWORD = registerItem("ancient_longsword", new SwordItem(ModToolMaterial.ANCIENT_INGOT, 7,-2.7f, new FabricItemSettings()));

    public static final Item ANCIENT_HELMET = registerItem("ancient_helmet", new ArmorItem(ModArmorMaterials.ANCIENT_INGOT, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ANCIENT_CHESTPLATE = registerItem("ancient_chestplate", new ArmorItem(ModArmorMaterials.ANCIENT_INGOT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ANCIENT_LEGGINGS = registerItem("ancient_leggings", new ArmorItem(ModArmorMaterials.ANCIENT_INGOT, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ANCIENT_BOOTS = registerItem("ancient_boots", new ArmorItem(ModArmorMaterials.ANCIENT_INGOT, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {

        entries.add(SILICA_GEL_PACKET);
        entries.add(MOLDABLE_PORCELAIN);
        entries.add(CLAY_SLIP);
        entries.add(SILICA);
        entries.add(REMNANT_SHARD);
        entries.add(MOLTEN_REMNANT);
        entries.add(UNCONDITIONED_ANCIENT_MOULD);
        entries.add(CONDITIONED_ANCIENT_MOULD);
        entries.add(CONDITIONED_ARMOR_MOULD);
        entries.add(CONDITIONED_WEAPON_MOULD);
        entries.add(UNREFINED_ANCIENT_AMALGAM);
        entries.add(ANCIENT_INGOT);
    }
    private static void addItemsToFoodAndDrinkItemsGroup(FabricItemGroupEntries entries) {
        entries.add(PINEAPPLE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AncientArch.MOD_ID, name), item);
    }

    private static void addItemsToCombatItemsGroup(FabricItemGroupEntries entries) {
        entries.add(ANCIENT_LONGSWORD);
        entries.add(ANCIENT_GREATAXE);
        entries.add(ANCIENT_HELMET);
        entries.add(ANCIENT_CHESTPLATE);
        entries.add(ANCIENT_LEGGINGS);
        entries.add(ANCIENT_BOOTS);
    }

    public static void registerModItems() {
        AncientArch.LOGGER.info("Registering Mod Items for " + AncientArch.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodAndDrinkItemsGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemsGroup);
    }
}
