package frosta.ancientarch.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class AncientArmorBootModel extends EntityModel<LivingEntity> {
    public final ModelPart root;
    public final ModelPart rightLeg;
    public final ModelPart rightBoot;
    public final ModelPart leftLeg;
    public final ModelPart leftBoot;
    public AncientArmorBootModel(ModelPart root) {
        this.root = root.getChild("root");
        this.rightLeg = this.root.getChild("rightLeg");
        this.rightBoot = this.rightLeg.getChild("rightBoot");
        this.leftLeg = this.root.getChild("leftLeg");
        this.leftBoot = this.leftLeg.getChild("leftBoot");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData rightLeg = root.addChild("rightLeg", ModelPartBuilder.create(), ModelTransform.pivot(-1.9F, -12.0F, 0.0F));

        ModelPartData rightBoot = rightLeg.addChild("rightBoot", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.8F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData leftLeg = root.addChild("leftLeg", ModelPartBuilder.create(), ModelTransform.pivot(1.9F, -12.0F, 0.0F));

        ModelPartData leftBoot = leftLeg.addChild("leftBoot", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.8F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }
    @Override
    public void setAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}