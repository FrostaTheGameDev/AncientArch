package frosta.ancientarch;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import frosta.ancientarch.datagen.*;


public class AncientDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(AncientBlockTagProvider::new);
		pack.addProvider(AncientItemTagProvider::new);
		pack.addProvider(AncientLootTableProvider::new);
		pack.addProvider(AncientModelProvider::new);
		pack.addProvider(AncientRecipeProvider::new);
	}
}
