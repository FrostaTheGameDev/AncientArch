package frosta.ancientarch.item;

import frosta.ancientarch.AncientArch;
import frosta.ancientarch.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup PORCELAIN_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(AncientArch.MOD_ID, "porcelain"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.porcelain_group"))
                    .icon(() -> new ItemStack(ModItems.SILICA_GEL_PACKET)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SILICA);
                        entries.add(ModItems.SILICA_GEL_PACKET);
                        entries.add(ModItems.CLAY_SLIP);
                        entries.add(ModItems.MOLDABLE_PORCELAIN);
                        entries.add(ModItems.REMNANT_SHARD);
                        entries.add(ModItems.MOLTEN_REMNANT);
                        entries.add(ModItems.UNCONDITIONED_ANCIENT_MOULD);
                        entries.add(ModItems.CONDITIONED_ANCIENT_MOULD);
                    }).build());

    public static final ItemGroup ANCIENT_BUILDING_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(AncientArch.MOD_ID, "ancient_building_block"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ancient_building_block_group"))
                    .icon(() -> new ItemStack(ModBlocks.CRACKED_END_PORTAL_FRAME)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.CRACKED_END_PORTAL_FRAME);

                    }).build());


    public static void registerItemGroups(){
        AncientArch.LOGGER.info ("Registering Item Groups for " + AncientArch.MOD_ID);
    }
}

