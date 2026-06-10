package frosta.ancientarch.datagen.provider;

import frosta.ancientarch.block.ArchBlocks;
import frosta.ancientarch.item.ArchItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.family.BlockFamily;

import java.util.List;

public class AncientModelProvider extends FabricModelProvider {
    public AncientModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool ancientPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ArchBlocks.ANCIENT_BLOCK);

        /// Ancient
        BlockFamily ANCIENT_FAMILY = new BlockFamily.Builder(ArchBlocks.ANCIENT_BLOCK)
                .build();

        ancientPool.family(ANCIENT_FAMILY);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        List.of(
                ArchItems.EMPTY_CORE,
                ArchItems.UNSTABLE_CORE,
                ArchItems.GAUNTLET_CORE,
                ArchItems.AUTUMN_SOUL,
                ArchItems.WINTER_SOUL,
                ArchItems.SPRING_SOUL,
                ArchItems.SUMMER_SOUL,
                ArchItems.ANCIENT_INGOT,
                ArchItems.UNREFINED_ANCIENT_AMALGAM,
                ArchItems.PINEAPPLE,
                ArchItems.PINEAPPLE_SEEDS,
                ArchItems.MOLTEN_REMNANT,
                ArchItems.REMNANT_SHARD,
                ArchItems.BOTTLED_MOLTEN_REMNANT,
                ArchItems.UNCONDITIONED_ANCIENT_MOULD,
                ArchItems.CONDITIONED_REMNANT_MOULD,
                ArchItems.CONDITIONED_WEAPON_MOULD,
                ArchItems.XENONITE_INGOT,
                ArchItems.XENONITE_CAGE,
                ArchItems.XENONITE_SCRAP,
                ArchItems.VOID_CARCASS

        ).forEach(item -> itemModelGenerator.register(item, Models.GENERATED));
    }
}
