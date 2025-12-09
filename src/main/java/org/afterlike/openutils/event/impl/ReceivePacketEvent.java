package org.afterlike.openutils.event.impl;

import net.minecraft.network.Packet;
import org.afterlike.openutils.event.api.Event;
import org.jetbrains.annotations.NotNull;

public class ReceivePacketEvent implements Event {
	private final @NotNull Packet<?> packet;
	public ReceivePacketEvent(@NotNull final Packet<?> packet) {
		this.packet = packet;
	}

	public @NotNull Packet<?> getPacket() {
		return packet;
	}
}
