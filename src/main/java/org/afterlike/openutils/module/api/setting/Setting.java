package org.afterlike.openutils.module.api.setting;

import org.jetbrains.annotations.NotNull;

public class Setting<T> {
	protected final @NotNull String name;
	protected T value;
	public Setting(@NotNull final String name) {
		this.name = name;
	}

	public @NotNull String getName() {
		return this.name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(@NotNull T value) {
		this.value = value;
	}
}
