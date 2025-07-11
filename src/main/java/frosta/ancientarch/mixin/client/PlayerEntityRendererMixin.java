package frosta.ancientarch.mixin.client;

import frosta.ancientarch.client.models.AncientArmorModel;
import frosta.ancientarch.client.render.AncientArmorFeatureRenderer;
import frosta.ancientarch.item.AncientArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Arm;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin
        extends
        LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    @Shadow
    protected abstract void setModelPose(AbstractClientPlayerEntity player);

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx,
                                     PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void ait$PlayerEntityRenderer(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        PlayerEntityRenderer renderer = (PlayerEntityRenderer) (Object) this;

        this.addFeature(new AncientArmorFeatureRenderer<>(renderer, ctx.getModelLoader()));
    }

    @Inject(method = "renderArm", at = @At("HEAD"))
    private void ait$renderArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        if (!(player.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof AncientArmorItem)) return;

        PlayerEntityModel<AbstractClientPlayerEntity> playerEntityModel = this.getModel();
        this.setModelPose(player);
        playerEntityModel.handSwingProgress = 0.0f;
        playerEntityModel.sneaking = false;
        playerEntityModel.leaningPitch = 0.0f;
        playerEntityModel.setAngles(player, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        arm.pitch = 0.0f;

        AncientArmorModel ancientArmorModel = new AncientArmorModel(AncientArmorModel.getTexturedModelData().createModel());

        boolean rightHanded = player.getMainArm() == Arm.RIGHT;

        if (rightHanded) {
            ancientArmorModel.rightArm.copyTransform(arm);
            ancientArmorModel.rightArm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(AncientArmorFeatureRenderer.TEXTURE)), light, OverlayTexture.DEFAULT_UV);
        } else {
            ancientArmorModel.leftArm.copyTransform(arm);
            ancientArmorModel.leftArm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(AncientArmorFeatureRenderer.TEXTURE)), light, OverlayTexture.DEFAULT_UV);
        }
    }
}
