package frosta.ancientarch;

import frosta.ancientarch.block.ArchBlocks;
import frosta.ancientarch.block.blockentity.ArchBlockEntitys;
import frosta.ancientarch.item.ArchItemGroups;
import frosta.ancientarch.item.ArchItems;
import frosta.ancientarch.recipe.ArchRecipes;
import frosta.ancientarch.screen.ArchScreenHandlers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AncientArch implements ModInitializer {
	public static final String MOD_ID = "ancient-arch";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ArchBlockEntitys.registerBlockEntities();
		ArchScreenHandlers.registerScreenHandlers();

		ArchItemGroups.registerItemGroups();

		ArchItems.registerModItems();
		ArchBlocks.registerModBlocks();

		ArchRecipes.registerRecipes();
	}
}