package frosta.ancientarch.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class AncientBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public AncientBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        addTag(BlockTags.AXE_MINEABLE
        );

        addTag(BlockTags.WALLS
        );

        addTag(BlockTags.PICKAXE_MINEABLE
        );

        addTag(BlockTags.FENCE_GATES
        );

        addTag(BlockTags.LOGS_THAT_BURN
        );

        addTag(BlockTags.PLANKS
        );

        addTag(BlockTags.LOGS_THAT_BURN
        );

        addTag(BlockTags.WOODEN_FENCES
        );

        addTag(BlockTags.FENCE_GATES
        );

        addTag(BlockTags.HOE_MINEABLE
        );

        addTag(BlockTags.AXE_MINEABLE
        );

        addTag(BlockTags.WOODEN_DOORS
        );

        addTag(BlockTags.DOORS
        );

        addTag(BlockTags.WOODEN_TRAPDOORS
        );

        addTag(BlockTags.TRAPDOORS
        );

        addTag(BlockTags.WOODEN_PRESSURE_PLATES
        );

        addTag(BlockTags.PRESSURE_PLATES
        );

        addTag(BlockTags.WOODEN_BUTTONS
        );
    }

    public void addTag(TagKey<Block> tagKey, Block... block) {
        getOrCreateTagBuilder(tagKey).add(block);
    }

    public void addTag(TagKey<Block> tagKey, Object... objects) {
        FabricTagBuilder builder = getOrCreateTagBuilder(tagKey);

        for(Object obj : objects) {
            if(obj instanceof Block block) {
                builder.add(block);
                continue;
            }
            if(obj instanceof Object[] objects1) {
                addTag(tagKey, objects1);
                continue;
            }
            if(obj instanceof TagKey<?> tagKey1) {
                builder.addTag((TagKey<Block>) tagKey1);
                continue;
            }
        }
    }
}