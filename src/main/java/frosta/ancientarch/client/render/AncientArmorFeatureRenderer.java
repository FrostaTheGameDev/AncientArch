package frosta.ancientarch.client.render;

import frosta.ancientarch.AncientArch;
import frosta.ancientarch.client.models.AncientArmorBootModel;
import frosta.ancientarch.client.models.AncientArmorModel;
import frosta.ancientarch.item.AncientArmorItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Identifier;

public class AncientArmorFeatureRenderer<T extends LivingEntity, M extends EntityModel<T> & ModelWithArms>
        extends
        FeatureRenderer<T, M> {

    public static final Identifier TEXTURE = new Identifier(AncientArch.MOD_ID, "textures/armor/ancient_armor.png");
    public static final Identifier BOOTS_TEXTURE = new Identifier(AncientArch.MOD_ID, "textures/armor/ancient_armor_boots.png");

    private final AncientArmorModel ancientArmorModel;
    private final AncientArmorBootModel ancientArmorBootModel;

    public AncientArmorFeatureRenderer(FeatureRendererContext<T, M> context, EntityModelLoader modelLoader) {
        super(context);
        this.ancientArmorModel = new AncientArmorModel(AncientArmorModel.getTexturedModelData().createModel());
        this.ancientArmorBootModel = new AncientArmorBootModel(AncientArmorBootModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        boolean hasHead = entity.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof AncientArmorItem;
        boolean hasChest = entity.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof AncientArmorItem;
        boolean hasLegs = entity.getEquippedStack(EquipmentSlot.LEGS).getItem() instanceof AncientArmorItem;
        boolean hasFeet = entity.getEquippedStack(EquipmentSlot.FEET).getItem() instanceof AncientArmorItem;

        if (!(hasHead || hasChest || hasLegs || hasFeet)) {
            return;
        }

        if (entity instanceof AbstractClientPlayerEntity || entity instanceof ArmorStandEntity) {
            matrices.push();
            matrices.translate(0, -1.5f, 0);

            this.ancientArmorModel.head.visible = hasHead;
            this.ancientArmorModel.body.visible = hasChest;
            this.ancientArmorModel.waist.visible = hasLegs;
            this.ancientArmorModel.rightArm.visible = hasChest;
            this.ancientArmorModel.rightLeg.visible = hasLegs;
            this.ancientArmorModel.leftArm.visible = hasChest;
            this.ancientArmorModel.leftLeg.visible = hasLegs;
            this.ancientArmorBootModel.rightLeg.visible = hasFeet;
            this.ancientArmorBootModel.leftLeg.visible = hasFeet;

            BipedEntityModel<?> contextModel = (BipedEntityModel<?>) this.getContextModel();

            if (hasHead) {
                this.ancientArmorModel.head.copyTransform(contextModel.head);
            }
            if (hasChest) {
                this.ancientArmorModel.body.copyTransform(contextModel.body);
                this.ancientArmorModel.rightArm.copyTransform(contextModel.rightArm);
                this.ancientArmorModel.leftArm.copyTransform(contextModel.leftArm);
            }
            if (hasLegs) {
                this.ancientArmorModel.waist.copyTransform(contextModel.body);
                this.ancientArmorModel.rightLeg.copyTransform(contextModel.rightLeg);
                this.ancientArmorModel.leftLeg.copyTransform(contextModel.leftLeg);
            }
            // temp
            if (hasFeet) {
                this.ancientArmorBootModel.rightLeg.copyTransform(contextModel.rightLeg);
                this.ancientArmorBootModel.leftLeg.copyTransform(contextModel.leftLeg);
            }

            this.ancientArmorModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            this.ancientArmorModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(TEXTURE)), light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
            this.ancientArmorBootModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            this.ancientArmorBootModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(BOOTS_TEXTURE)), light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
            matrices.pop();

        }
    }
}
