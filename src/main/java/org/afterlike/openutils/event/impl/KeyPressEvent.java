package org.afterlike.openutils.event.impl;

import org.afterlike.openutils.event.api.Event;

public class KeyPressEvent implements Event {
	private final int keyCode;
	private final boolean pressed;
	public KeyPressEvent(final int keyCode, final boolean pressed) {
		this.keyCode = keyCode;
		this.pressed = pressed;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public boolean isPressed() {
		return pressed;
	}
}
