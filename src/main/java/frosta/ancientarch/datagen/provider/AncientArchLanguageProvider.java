package frosta.ancientarch.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AncientArchLanguageProvider extends FabricLanguageProvider {
    public AncientArchLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add("item.ancient-arch.silica_gel_packet", "Silica Gel Packet");
        translationBuilder.add("item.ancient-arch.moldable_porcelain", "Moldable Porcelain");
        translationBuilder.add("item.ancient-arch.clay_slip", "Clay Slip");
        translationBuilder.add("item.ancient-arch.silica", "Silica");
        translationBuilder.add("item.ancient-arch.remnant_shard", "Remnant Shard");
        translationBuilder.add("item.ancient-arch.molten_remnant", "Molten Remnant");
        translationBuilder.add("item.ancient-arch.unconditioned_ancient_mould", "Unconditioned Ancient Mould");
        translationBuilder.add("item.ancient-arch.conditioned_ancient_mould", "Conditioned Ancient Mould");
        translationBuilder.add("item.ancient-arch.conditioned_armor_mould", "Conditioned Armor Mould");
        translationBuilder.add("item.ancient-arch.conditioned_weapon_mould", "Conditioned Weapon Mould");
        translationBuilder.add("item.ancient-arch.hammer_and_chisel", "Hammer N' Chisel");
        translationBuilder.add("item.ancient-arch.ancient_ingot", "Ancient Ingot");
        translationBuilder.add("item.ancient-arch.unrefined_ancient_amalgam", "Unrefined Ancient Amalgam");
        translationBuilder.add("item.ancient-arch.pineapple", "Pineapple");
        translationBuilder.add("item.ancient-arch.ancient_greataxe", "Ancient Greataxe");
        translationBuilder.add("item.ancient-arch.ancient_longsword", "Ancient Longsword");
        translationBuilder.add("item.ancient-arch.ancient_helmet", "Ancient Helmet");
        translationBuilder.add("item.ancient-arch.ancient_chestplate", "Ancient Chestplate");
        translationBuilder.add("item.ancient-arch.ancient_leggings", "Ancient Leggings");
        translationBuilder.add("item.ancient-arch.ancient_boots", "Ancient Boots");
        translationBuilder.add("block.ancient-arch.cracked_end_portal_frame", "Cracked End Portal Frame");
        translationBuilder.add("block.ancient-arch.cracked_end_portal_frame_used", "Used Cracked End Portal Frame");
        translationBuilder.add("block.ancient-arch.ancient_block", "Ancient Block");
        translationBuilder.add("itemgroup.ancient_arch", "Ancient Arch");
    }
}
