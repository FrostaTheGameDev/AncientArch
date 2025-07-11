package frosta.ancientarch.item;

import frosta.ancientarch.AncientArch;
import frosta.ancientarch.block.ArchBlocks;
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
                    .icon(() -> new ItemStack(ArchItems.ANCIENT_GREATAXE)).entries((displayContext, entries) -> {
                        entries.add(ArchItems.UNREFINED_ANCIENT_AMALGAM);
                        entries.add(ArchItems.ANCIENT_INGOT);
                        entries.add(ArchBlocks.ANCIENT_BLOCK);
                        entries.add(ArchItems.PINEAPPLE);
                        entries.add(ArchItems.SILICA);
                        entries.add(ArchItems.SILICA_GEL_PACKET);
                        entries.add(ArchItems.CLAY_SLIP);
                        entries.add(ArchItems.MOLDABLE_PORCELAIN);
                        entries.add(ArchItems.REMNANT_SHARD);
                        entries.add(ArchItems.MOLTEN_REMNANT);
                        entries.add(ArchItems.UNCONDITIONED_ANCIENT_MOULD);
                        entries.add(ArchItems.CONDITIONED_ANCIENT_MOULD);
                        entries.add(ArchItems.CONDITIONED_ARMOR_MOULD);
                        entries.add(ArchItems.CONDITIONED_WEAPON_MOULD);
                        entries.add(ArchItems.ANCIENT_LONGSWORD);
                        entries.add(ArchItems.ANCIENT_GREATAXE);
                        entries.add(ArchItems.HAMMER_AND_CHISEL);
                        entries.add(ArchItems.ANCIENT_HELMET);
                        entries.add(ArchItems.ANCIENT_CHESTPLATE);
                        entries.add(ArchItems.ANCIENT_LEGGINGS);
                        entries.add(ArchItems.ANCIENT_BOOTS);
                    }).build());


    public static void registerItemGroups(){
        AncientArch.LOGGER.info ("Registering Item Groups for " + AncientArch.MOD_ID);
    }
}

