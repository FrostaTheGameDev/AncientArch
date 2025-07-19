package frosta.ancientarch.block;

import frosta.ancientarch.AncientArch;
import frosta.ancientarch.block.custom.CrackedEndPortalFrameBlock;
import frosta.ancientarch.block.custom.WetPorcelainBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArchBlocks {
    public static final Block CRACKED_END_PORTAL_FRAME = registerBlock("cracked_end_portal_frame",
            new CrackedEndPortalFrameBlock(FabricBlockSettings.copyOf(Blocks.END_PORTAL_FRAME)));
    public static final Block CRACKED_END_PORTAL_FRAME_USED = registerBlock("cracked_end_portal_frame_used",
            new EndPortalFrameBlock(FabricBlockSettings.copyOf(Blocks.END_PORTAL_FRAME)));
public static final Block ANCIENT_BLOCK = registerBlock("ancient_block",
        new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
public static final Block WET_PORCELAIN_BLOCK = registerBlock("wet_porcelain_block",
        new WetPorcelainBlock(FabricBlockSettings.copyOf(Blocks.MUD)));
public static final Block DRIED_PORCELAIN_BLOCK = registerBlock("dried_porcelain_block",
        new GlassBlock(FabricBlockSettings.copyOf(Blocks.GLASS)));
    public static final Block KILN = registerBlock("kiln",
            new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK,new Identifier(AncientArch.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(AncientArch.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        AncientArch.LOGGER.info("Registering ModBlocks for " + AncientArch.MOD_ID);
    }
}
