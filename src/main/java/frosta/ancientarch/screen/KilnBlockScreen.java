package frosta.ancientarch.screen;
import com.mojang.blaze3d.systems.RenderSystem;
import frosta.ancientarch.AncientArch;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class KilnBlockScreen extends HandledScreen<KilnBlockScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(AncientArch.MOD_ID, "textures/gui/kiln_gui.png");

    public KilnBlockScreen(KilnBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
        renderFuelFlame(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, 8, handler.getScaledProgress());
        }
    }

    private void renderFuelFlame(DrawContext context, int x, int y) {
        int fuelTime = handler.getFuelTime();
        int fuelTimeMax = handler.getFuelTimeMax();
        if (fuelTimeMax > 0) {
            int flameHeight = (int)(14f * ((float) fuelTime / fuelTimeMax));
            context.drawTexture(TEXTURE, x + 117, y + 43 + 1 - flameHeight, 176, 41 - flameHeight, 14, flameHeight);
        }
    }

        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            renderBackground(context, mouseX, mouseY, delta);
            super.render(context, mouseX, mouseY, delta);
            drawMouseoverTooltip(context, mouseX, mouseY);
        }

        private void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {

        }
    }
