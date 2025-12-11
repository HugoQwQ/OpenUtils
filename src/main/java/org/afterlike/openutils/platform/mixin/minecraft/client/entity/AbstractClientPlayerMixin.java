package org.afterlike.openutils.platform.mixin.minecraft.client.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import org.afterlike.openutils.OpenUtils;
import org.afterlike.openutils.module.impl.render.CapeModule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerMixin {
	@Inject(method = "getLocationCape", at = @At("HEAD"), cancellable = true)
	public void ou$getLocationCape(CallbackInfoReturnable<ResourceLocation> cir) {
		if (!OpenUtils.get().getModuleHandler().isEnabled(CapeModule.class))
			return;
		if ((AbstractClientPlayer) (Object) this != Minecraft.getMinecraft().thePlayer)
			return;
		cir.setReturnValue(OpenUtils.get().getModuleHandler().getModuleClass(CapeModule.class)
				.getCapeLocation());
	}
}
