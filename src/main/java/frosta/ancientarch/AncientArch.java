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

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ArchBlockEntitys.registerBlockEntities();
		ArchScreenHandlers.registerScreenHandlers();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ArchItemGroups.registerItemGroups();
		ArchItems.registerModItems();
		ArchBlocks.registerModBlocks();
		ArchRecipes.registerRecipes();
	}
}