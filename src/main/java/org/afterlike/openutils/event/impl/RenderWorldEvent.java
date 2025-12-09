package org.afterlike.openutils.event.impl;

import org.afterlike.openutils.event.api.Event;

public class RenderWorldEvent implements Event {
	private final float partialTicks;
	public RenderWorldEvent(final float partialTicks) {
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return partialTicks;
	}
}
