package org.afterlike.openutils.platform.mixin.minecraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.afterlike.openutils.OpenUtils;
import org.afterlike.openutils.event.api.EventPhase;
import org.afterlike.openutils.event.impl.GameTickEvent;
import org.afterlike.openutils.event.impl.KeyPressEvent;
import org.afterlike.openutils.event.impl.ResizeWindowEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
	@Inject(method = "startGame", at = @At("HEAD"))
	private void startGame$head(final @NotNull CallbackInfo callbackInfo) {
		OpenUtils.get().initialize();
	}

	@Inject(method = "startGame", at = @At(value = "CONSTANT", args = "stringValue=Post startup"))
	private void ou$startGame$postStartup(@NotNull final CallbackInfo ci) {
		OpenUtils.get().lateInitialize();
	}

	@Inject(method = "updateFramebufferSize", at = @At("RETURN"))
	private void ou$updateFramebufferSize(@NotNull final CallbackInfo ci) {
		OpenUtils.get().getEventBus().post(new ResizeWindowEvent());
	}

	@Redirect(method = "runTick", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/client/settings/KeyBinding;setKeyBindState(IZ)V"))
	private void ou$setKeyBindState(final int keyCode, final boolean pressed) {
		KeyBinding.setKeyBindState(keyCode, pressed);
		OpenUtils.get().getEventBus().post(new KeyPressEvent(keyCode, pressed));
	}

	@Inject(method = "runTick", at = @At("HEAD"))
	private void ou$runTick$head(@NotNull final CallbackInfo ci) {
		OpenUtils.get().getEventBus().post(new GameTickEvent(EventPhase.PRE));
	}

	@Inject(method = "runTick", at = @At("RETURN"))
	private void ou$runTick$return(@NotNull final CallbackInfo ci) {
		OpenUtils.get().getEventBus().post(new GameTickEvent(EventPhase.POST));
	}
}
