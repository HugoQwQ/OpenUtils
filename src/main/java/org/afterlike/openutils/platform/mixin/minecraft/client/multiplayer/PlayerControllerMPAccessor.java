package org.afterlike.openutils.platform.mixin.minecraft.client.multiplayer;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = PlayerControllerMP.class)
public interface PlayerControllerMPAccessor {
	@Accessor(value = "blockHitDelay")
	public void ou$setBlockHitDelay(int delay);

	@Accessor(value = "blockHitDelay")
	public int ou$getBlockHitDelay();
}
