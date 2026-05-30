package frosta.ancientarch.item;

import frosta.ancientarch.AncientArch;
import frosta.ancientarch.block.ArchBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ArchItems {
    ///Xenonite
    public static final Item XENONITE_INGOT = registerItem("xenonite_ingot", new Item(new FabricItemSettings().fireproof()));
    public static final Item XENONITE_SCRAP = registerItem("xenonite_scrap", new Item(new FabricItemSettings().fireproof()));
    public static final Item XENONITE_CAGE = registerItem("xenonite_cage", new XenoniteCageItem(new FabricItemSettings().fireproof()));


    ///Void
    public static final Item VOID_CARCASS = registerItem("void_carcass", new XenoniteCageItem(new FabricItemSettings().fireproof()));


    ///Remnant
    public static final Item REMNANT_SHARD = registerItem("remnant_shard", new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item MOLTEN_REMNANT = registerItem("molten_remnant", new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item UNCONDITIONED_REMNANT_MOULD = registerItem("unconditioned_ancient_mould", new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item CONDITIONED_REMNANT_MOULD = registerItem("conditioned_ancient_mould", new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item CONDITIONED_WEAPON_MOULD = registerItem("conditioned_weapon_mould", new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));

    ///Ancient
    public static final Item ANCIENT_INGOT = registerItem("ancient_ingot", new Item(new FabricItemSettings().fireproof()));
    public static final Item UNREFINED_ANCIENT_AMALGAM = registerItem("unrefined_ancient_amalgam", new Item(new FabricItemSettings().fireproof()));

    ///Random shit
    public static final Item HAMMER_AND_CHISEL = registerItem("hammer_and_chisel", new HammerAndChiselItem(ToolMaterials.NETHERITE, new FabricItemSettings().maxCount(1)));

    /// Ancient Core stuff/Gauntlet core stuff
    public static final Item GAUNTLET_CORE = registerItem("gauntlet_core", new Item(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1).fireproof()));
    public static final Item UNSTABLE_CORE = registerItem("unstable_core", new UnstableCoreItem(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1).fireproof()));
    public static final Item EMPTY_CORE = registerItem("empty_core", new Item(new FabricItemSettings().maxCount(1)));

    /// Soul shit
    public static final Item AUTUMN_SOUL = registerItem("autumn_soul", new Item(new FabricItemSettings().fireproof().rarity(Rarity.RARE).maxCount(1)));
    public static final Item WINTER_SOUL = registerItem("winter_soul", new Item(new FabricItemSettings().fireproof().rarity(Rarity.RARE).maxCount(1)));
    public static final Item SPRING_SOUL = registerItem("spring_soul", new Item(new FabricItemSettings().fireproof().rarity(Rarity.RARE).maxCount(1)));
    public static final Item SUMMER_SOUL = registerItem("summer_soul", new Item(new FabricItemSettings().fireproof().rarity(Rarity.RARE).maxCount(1)));
    public static final Item HOLY_CORE = registerItem("holy_core", new HolyCoreItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE).fireproof()));

    /// Foods and seeds
    public static final Item PINEAPPLE = registerItem("pineapple", new Item(new FabricItemSettings().food(ArchFoodComponents.PINEAPPLE)));
    public static final Item PINEAPPLE_SEEDS = registerItem("pineapple_seeds",
            new AliasedBlockItem(ArchBlocks.PINEAPPLE_CROP, new FabricItemSettings()));

    /// Weapons
    public static final Item PURE_WINTER = registerItem("pure_winter", new AxeItem(ArchToolMaterial.HOLY,8,-3f,new FabricItemSettings().fireproof()));
    public static final Item FALLEN_LONGSWORD = registerItem("fallen_longsword", new SwordItem(ArchToolMaterial.HOLY,7,-2.6f, new FabricItemSettings().fireproof()));
    public static final Item ANCIENT_GREATAXE = registerItem("ancient_greataxe", new AxeItem(ArchToolMaterial.ANCIENT_INGOT, 8.5f,-3f, new FabricItemSettings().fireproof()));
    public static final Item ANCIENT_LONGSWORD = registerItem("ancient_longsword", new SwordItem(ArchToolMaterial.ANCIENT_INGOT, 7,-2.7f, new FabricItemSettings().fireproof()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AncientArch.MOD_ID, name), item);
    }

    public static void registerModItems() {
        AncientArch.LOGGER.info("Registering Mod Items for " + AncientArch.MOD_ID);
    }
}

