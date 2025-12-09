package org.afterlike.openutils.event.impl;

import org.afterlike.openutils.event.api.Event;
import org.afterlike.openutils.event.api.EventPhase;
import org.jetbrains.annotations.NotNull;

public class GameTickEvent implements Event {
	private final @NotNull EventPhase phase;
	public GameTickEvent(@NotNull final EventPhase phase) {
		this.phase = phase;
	}

	public @NotNull EventPhase getPhase() {
		return phase;
	}
}
