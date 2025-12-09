package org.afterlike.openutils.platform.mixin.minecraft.client.renderer;

import net.minecraft.client.renderer.EntityRenderer;
import org.afterlike.openutils.OpenUtils;
import org.afterlike.openutils.event.impl.RenderWorldEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
	@Inject(method = "renderWorldPass", at = @At("RETURN"))
	private void ou$renderWorldPass(final int pass, final float partialTicks,
			final long finishTimeNano, @NotNull final CallbackInfo ci) {
		OpenUtils.get().getEventBus().post(new RenderWorldEvent(partialTicks));
	}
}
