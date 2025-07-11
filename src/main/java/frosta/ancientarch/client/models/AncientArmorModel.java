package frosta.ancientarch.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class AncientArmorModel extends EntityModel<LivingEntity> {
	public final ModelPart root;
	public final ModelPart head;
	public final ModelPart horns;
	public final ModelPart body;
	public final ModelPart rightArm;
	public final ModelPart leftArm;
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;
	public AncientArmorModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.horns = this.head.getChild("horns");
		this.body = this.root.getChild("body");
		this.rightArm = this.root.getChild("rightArm");
		this.leftArm = this.root.getChild("leftArm");
		this.rightLeg = this.root.getChild("rightLeg");
		this.leftLeg = this.root.getChild("leftLeg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.3F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData horns = head.addChild("horns", ModelPartBuilder.create(), ModelTransform.of(0.0F, -8.5329F, -0.7627F, 1.4399F, 0.0F, 0.0F));

		ModelPartData cube_r1 = horns.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(-0.1F)), ModelTransform.of(4.0463F, 0.0682F, -0.0625F, -0.7182F, -0.2046F, -0.2284F));

		ModelPartData cube_r2 = horns.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 0).cuboid(-9.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 3.5329F, -3.2373F, -0.7418F, 0.0F, 0.0F));

		ModelPartData cube_r3 = horns.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(-0.1F)), ModelTransform.of(-4.0463F, 0.0682F, -0.0625F, -0.7182F, 0.2046F, 0.2284F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.3F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData rightArm = root.addChild("rightArm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(-5.0F, -22.0F, 0.0F));

		ModelPartData leftArm = root.addChild("leftArm", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.5F)).mirrored(false), ModelTransform.pivot(5.0F, -22.0F, 0.0F));

		ModelPartData rightLeg = root.addChild("rightLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.3F)), ModelTransform.pivot(-1.9F, -12.0F, 0.0F));

		ModelPartData leftLeg = root.addChild("leftLeg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.3F)).mirrored(false), ModelTransform.pivot(1.9F, -12.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(LivingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}