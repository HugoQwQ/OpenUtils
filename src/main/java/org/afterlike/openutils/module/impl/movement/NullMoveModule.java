package org.afterlike.openutils.module.impl.movement;

import org.afterlike.openutils.module.api.Module;
import org.afterlike.openutils.module.api.ModuleCategory;
import org.afterlike.openutils.module.api.setting.impl.DescriptionSetting;

public class NullMoveModule extends Module {
	public static DescriptionSetting description;
	public static long LEFT_LAST_PRESS_TIME;
	public static long RIGHT_LAST_PRESS_TIME;
	public static long FORWARD_LAST_PRESS_TIME;
	public static long BACKWARD_LAST_PRESS_TIME;
	public NullMoveModule() {
		super("NullMove", ModuleCategory.MOVEMENT);
		this.registerSetting(description = new DescriptionSetting(
				"Prevents opposite movement inputs from canceling each other"));
	}
}
