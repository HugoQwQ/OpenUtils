package org.afterlike.openutils.event.impl;

import org.afterlike.openutils.event.api.Event;
import org.jetbrains.annotations.NotNull;

public class ReceiveChatEvent implements Event {
	private final @NotNull String message;
	public ReceiveChatEvent(@NotNull final String message) {
		this.message = message;
	}

	public @NotNull String getMessage() {
		return message;
	}
}
