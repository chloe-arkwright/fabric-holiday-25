package holiday.idkwheretoputthis;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.WitherEntityModel;
import net.minecraft.client.render.entity.state.WitherEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;

// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
@Mixin(WitherEntityModel.class)
public class New_Tather extends EntityModel<WitherEntityRenderState> {
	private final ModelPart head1;
	private final ModelPart head2;
	private final ModelPart head3;
	private final ModelPart body1;
	private final ModelPart body2;
	private final ModelPart body3;
    private New_Tather(ModelPart root) {
        super(root);
    }
	public New_Tather(ModelPart root) {
		this.head1 = root.getChild("head1");
		this.head2 = root.getChild("head2");
		this.head3 = root.getChild("head3");
		this.body1 = root.getChild("body1");
		this.body2 = root.getChild("body2");
		this.body3 = root.getChild("body3");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head1 = modelPartData.addChild("head1", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 12.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head2 = modelPartData.addChild("head2", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -9.0F, -5.0F, 8.0F, 12.0F, 8.0F, new Dilation(-1.0F)), ModelTransform.pivot(-8.0F, 4.0F, 0.0F));

		ModelPartData head3 = modelPartData.addChild("head3", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -9.0F, -5.0F, 8.0F, 12.0F, 8.0F, new Dilation(-1.0F)), ModelTransform.pivot(10.0F, 4.0F, 0.0F));

		ModelPartData body1 = modelPartData.addChild("body1", ModelPartBuilder.create().uv(0, 20).cuboid(-10.0F, 3.9F, -0.5F, 20.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body2 = modelPartData.addChild("body2", ModelPartBuilder.create().uv(0, 26).cuboid(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F))
		.uv(24, 26).cuboid(-4.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(24, 26).cuboid(-4.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(24, 26).cuboid(-4.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 6.9F, -0.5F));

		ModelPartData body3 = modelPartData.addChild("body3", ModelPartBuilder.create().uv(12, 26).cuboid(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 16.9F, -0.5F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		head1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		head2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		head3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
