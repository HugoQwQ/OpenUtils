package org.afterlike.openutils.platform.mixin.minecraft.client.renderer;

import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityRenderer.class)
public interface EntityRendererAccessor {
	@Accessor("thirdPersonDistance")
	void ou$setThirdPersonDistance(float distance);
}
