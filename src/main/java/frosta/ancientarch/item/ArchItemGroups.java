package frosta.ancientarch.item;

import frosta.ancientarch.AncientArch;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ArchItemGroups {
    public static final ItemGroup ANCIENT_ARCH = Registry.register(Registries.ITEM_GROUP,
            new Identifier(AncientArch.MOD_ID, "ancient_arch"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ancient_arch"))
                    .icon(() -> new ItemStack(ArchItems.EMPTY_CORE)).entries((displayContext, entries) -> {
                        entries.add(ArchItems.EMPTY_CORE);
                    }).build());


    public static void registerItemGroups(){
        AncientArch.LOGGER.info ("Registering Item Groups for " + AncientArch.MOD_ID);
    }
}

