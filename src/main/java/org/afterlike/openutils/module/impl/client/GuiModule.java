package org.afterlike.openutils.module.impl.client;

import org.afterlike.openutils.OpenUtils;
import org.afterlike.openutils.gui.ClickGuiScreen;
import org.afterlike.openutils.module.api.Module;
import org.afterlike.openutils.module.api.ModuleCategory;
import org.afterlike.openutils.module.api.setting.impl.ModeSetting;
import org.afterlike.openutils.util.client.ClientUtil;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;

public class GuiModule extends Module {
	public static ModeSetting theme;
	public GuiModule() {
		super("Gui", ModuleCategory.CLIENT, Keyboard.KEY_RSHIFT);
		this.registerSetting(
				theme = new ModeSetting("Theme", "Raven B3", "Raven B1", "Raven B2", "Raven B3"));
	}

	@Override
	public void onEnable() {
		if (ClientUtil.notNull() && !(mc.currentScreen instanceof ClickGuiScreen)) {
			@NotNull final ClickGuiScreen screen = OpenUtils.get().getClickGuiScreen();
			mc.displayGuiScreen(screen);
		}
		this.disable();
	}
}
