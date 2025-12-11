package org.afterlike.openutils.module.impl.minigames;

import org.afterlike.openutils.module.api.Module;
import org.afterlike.openutils.module.api.ModuleCategory;
import org.afterlike.openutils.module.api.setting.impl.DescriptionSetting;
import org.afterlike.openutils.module.api.setting.impl.NumberSetting;

public class LevelHiderModule extends Module {
	private final DescriptionSetting desc;
	private final NumberSetting level;
	public LevelHiderModule() {
		super("Level Hider", ModuleCategory.MINIGAMES);
		desc = this.registerSetting(new DescriptionSetting("Currently only for Hypixel Bed Wars"));
		level = this.registerSetting(new NumberSetting("Bed Wars Level", 727, 0, 10000, 1));
	}
}
