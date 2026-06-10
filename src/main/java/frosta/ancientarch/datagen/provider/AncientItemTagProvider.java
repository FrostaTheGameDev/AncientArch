package frosta.ancientarch.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class AncientItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public AncientItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        addTag(ItemTags.PLANKS
        );

        addTag(ItemTags.LOGS_THAT_BURN
        );

        addTag(ItemTags.WOODEN_DOORS
        );

        addTag(ItemTags.DOORS
        );

        addTag(ItemTags.WOODEN_TRAPDOORS
        );

        addTag(ItemTags.TRAPDOORS
        );

        addTag(ItemTags.WOODEN_PRESSURE_PLATES
        );

        addTag(ItemTags.WOODEN_BUTTONS
        );
    }

    public void addTag(TagKey<Item> tagKey, ItemConvertible... itemConvertibles) {
        getOrCreateTagBuilder(tagKey).add(Arrays.stream(itemConvertibles).map(ItemConvertible::asItem).toArray(Item[]::new));
    }

    public void addTag(TagKey<Item> tagKey, Object... objects) {
        FabricTagBuilder builder = getOrCreateTagBuilder(tagKey);

        for (Object obj : objects) {
            if(obj instanceof Item item) {
                builder.add(item);
                continue;
            }
            if(obj instanceof ItemConvertible itemConvertible) {
                builder.add(itemConvertible.asItem());
                continue;
            }
            if(obj instanceof Object[] objects1) {
                addTag(tagKey, objects1);
                continue;
            }
            if(obj instanceof TagKey<?> tagKey1) {
                builder.addTag((TagKey<Item>) tagKey1);
                continue;
            }
        }
    }
}