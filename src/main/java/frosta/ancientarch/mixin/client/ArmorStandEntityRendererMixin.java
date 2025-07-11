package frosta.ancientarch.mixin.client;

import frosta.ancientarch.client.render.AncientArmorFeatureRenderer;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.ArmorStandArmorEntityModel;
import net.minecraft.client.render.entity.model.ArmorStandEntityModel;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ArmorStandEntityRenderer.class)
public abstract class ArmorStandEntityRendererMixin
        extends
            LivingEntityRenderer<ArmorStandEntity, ArmorStandArmorEntityModel> {

    public ArmorStandEntityRendererMixin(EntityRendererFactory.Context ctx, ArmorStandEntityModel model,
                                         float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void ait$armorStandEntityRenderer(EntityRendererFactory.Context ctx, CallbackInfo ci) {
        ArmorStandEntityRenderer renderer = (ArmorStandEntityRenderer) (Object) this;

        this.addFeature(new AncientArmorFeatureRenderer<>(renderer, ctx.getModelLoader()));
    }
}
