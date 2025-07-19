package frosta.ancientarch.block.custom;

import frosta.ancientarch.block.ArchBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.MudBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class WetPorcelainBlock extends MudBlock {
    public WetPorcelainBlock(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient) {
            world.scheduleBlockTick(pos, this, 2500);
        }
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, ArchBlocks.DRIED_PORCELAIN_BLOCK.getDefaultState(), 3);
    }
}