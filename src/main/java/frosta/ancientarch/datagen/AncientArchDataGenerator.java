package frosta.ancientarch.datagen;

import frosta.ancientarch.datagen.provider.AncientArchLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AncientArchDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		/// Data-generate language for En-US
		pack.addProvider(AncientArchLanguageProvider::new);
	}
}
