package frosta.ancientarch.screen;

import frosta.ancientarch.AncientArch;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ArchScreenHandlers {
    public static final ScreenHandlerType<KilnBlockScreenHandler> KILN_BLOCK_SCREEN_HANDLER =
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(AncientArch.MOD_ID, "kiln"),
                new ExtendedScreenHandlerType<>(KilnBlockScreenHandler::new));

    public static void registerScreenHandlers() {
        AncientArch.LOGGER.info("Registering Screen Handlers for " + AncientArch.MOD_ID);
    }
}