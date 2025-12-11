package org.afterlike.openutils.module.impl.render;

import org.afterlike.openutils.module.api.Module;
import org.afterlike.openutils.module.api.ModuleCategory;
import org.afterlike.openutils.module.api.setting.impl.DescriptionSetting;
import org.afterlike.openutils.module.api.setting.impl.NumberSetting;

public class ThickRodsModule extends Module {
	private final DescriptionSetting desc;
	private final NumberSetting thickness;
	public ThickRodsModule() {
		super("Thick Rods", ModuleCategory.RENDER);
		desc = this.registerSetting(
				new DescriptionSetting("Modifies the thickness of your cast fishing rod line"));
		thickness = this.registerSetting(new NumberSetting("Line Thickness", 4, 0, 10, 1));
	}
}
