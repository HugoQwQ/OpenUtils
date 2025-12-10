package org.afterlike.openutils.platform.mixin.minecraft.client.renderer;

import java.util.Objects;
import net.minecraft.client.renderer.EntityRenderer;
import org.afterlike.openutils.OpenUtils;
import org.afterlike.openutils.event.impl.RenderWorldEvent;
import org.afterlike.openutils.module.api.setting.impl.NumberSetting;
import org.afterlike.openutils.module.impl.render.CameraModule;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
	@ModifyArg(method = "hurtCameraEffect",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V"),
			index = 0)
	private float ou$modifyHurtCameraEffect(float angle) {
		if (OpenUtils.get().getModuleHandler().isEnabled(CameraModule.class)) {
			return angle / 14 * Objects
					.requireNonNull(
							OpenUtils.get().getModuleHandler().getModuleClass(CameraModule.class)
									.getSetting("Hurt Shake Multiplier", NumberSetting.class))
					.getFloat();
		}
		return angle;
	}

	@Inject(method = "renderWorldPass", at = @At("RETURN"))
	private void ou$renderWorldPass(final int pass, final float partialTicks,
			final long finishTimeNano, @NotNull final CallbackInfo ci) {
		OpenUtils.get().getEventBus().post(new RenderWorldEvent(partialTicks));
	}
}
