package org.afterlike.openutils.module.impl.client;

import org.afterlike.openutils.module.api.Module;
import org.afterlike.openutils.module.api.ModuleCategory;
import org.afterlike.openutils.util.client.ClientUtil;

public class DebugModule extends Module {
	public DebugModule() {
		super("Debug", ModuleCategory.CLIENT);
	}

	@Override
	protected void onEnable() {
		ClientUtil.sendDebugMessage("debug enabled");
	}
}
