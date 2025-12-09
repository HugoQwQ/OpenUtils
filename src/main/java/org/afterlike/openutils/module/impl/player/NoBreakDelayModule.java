package org.afterlike.openutils.module.impl.player;

import org.afterlike.openutils.event.handler.EventHandler;
import org.afterlike.openutils.event.impl.GameTickEvent;
import org.afterlike.openutils.module.api.Module;
import org.afterlike.openutils.module.api.ModuleCategory;
import org.afterlike.openutils.module.api.setting.impl.DescriptionSetting;
import org.afterlike.openutils.module.api.setting.impl.NumberSetting;
import org.afterlike.openutils.platform.mixin.minecraft.client.multiplayer.PlayerControllerMPAccessor;
import org.afterlike.openutils.util.client.ClientUtil;

public class NoBreakDelayModule extends Module {
	public static DescriptionSetting description;
	public static NumberSetting delay;
	public NoBreakDelayModule() {
		super("No Break Delay", ModuleCategory.PLAYER);
		this.registerSetting(
				description = new DescriptionSetting("Reduces the delay between block breaks"));
		this.registerSetting(delay = new NumberSetting("Delay (ticks)", 0, 0, 5, 1));
	}

	@EventHandler
	public void onTick(GameTickEvent event) {
		if (ClientUtil.notNull()) {
			PlayerControllerMPAccessor accessor = (PlayerControllerMPAccessor) mc.playerController;
			if (accessor.ou$getBlockHitDelay() > delay.getInt()) {
				accessor.ou$setBlockHitDelay(delay.getInt());
			}
		}
	}
}
