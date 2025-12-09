package org.afterlike.openutils.platform.mixin.minecraftforge.fml.common;

import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.event.FMLEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.afterlike.openutils.OpenUtils;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LoadController.class)
public abstract class LoadControllerMixin {
	@Inject(method = "propogateStateMessage", at = @At("HEAD"), remap = false)
	private void onPropagateState(@NotNull final FMLEvent event, @NotNull final CallbackInfo ci) {
		if (event instanceof FMLInitializationEvent) {
			OpenUtils.get().initialize();
		}
	}
}
