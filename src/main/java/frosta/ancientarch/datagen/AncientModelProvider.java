package frosta.ancientarch.datagen;

import frosta.ancientarch.block.ArchBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.family.BlockFamily;

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

    }
}
