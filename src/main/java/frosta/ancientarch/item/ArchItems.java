package frosta.ancientarch.item;

import frosta.ancientarch.AncientArch;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ArchItems {
    public static final Item SILICA_GEL_PACKET = registerItem("silica_gel_packet", new Item(new FabricItemSettings().food(ArchFoodComponents.SILICA_GEL_PACKET)));
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
    public static final Item PINEAPPLE = registerItem("pineapple", new Item(new FabricItemSettings().food(ArchFoodComponents.PINEAPPLE)));

    public static final Item ANCIENT_GREATAXE = registerItem("ancient_greataxe", new GreatAxeItem(ArchToolMaterial.ANCIENT_INGOT, 9,-2.9f, new FabricItemSettings()));
    public static final Item ANCIENT_LONGSWORD = registerItem("ancient_longsword", new LongswordItem(ArchToolMaterial.ANCIENT_INGOT, 7,-2.5f,  new FabricItemSettings()));

    public static final Item ANCIENT_HELMET = registerItem("ancient_helmet", new AncientArmorItem(ArchArmorMaterials.ANCIENT, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ANCIENT_CHESTPLATE = registerItem("ancient_chestplate", new AncientArmorItem(ArchArmorMaterials.ANCIENT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ANCIENT_LEGGINGS = registerItem("ancient_leggings", new AncientArmorItem(ArchArmorMaterials.ANCIENT, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ANCIENT_BOOTS = registerItem("ancient_boots", new AncientArmorItem(ArchArmorMaterials.ANCIENT, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AncientArch.MOD_ID, name), item);
    }

    public static void registerModItems() {
        AncientArch.LOGGER.info("Registering Mod Items for " + AncientArch.MOD_ID);
    }
}
