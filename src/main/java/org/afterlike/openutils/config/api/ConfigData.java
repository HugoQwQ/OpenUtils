package org.afterlike.openutils.config.api;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class ConfigData {
	public @NotNull Map<@NotNull String, @NotNull ModuleEntry> modules = new HashMap<>();
	public @NotNull Map<@NotNull String, @NotNull PanelEntry> panels = new HashMap<>();
}
