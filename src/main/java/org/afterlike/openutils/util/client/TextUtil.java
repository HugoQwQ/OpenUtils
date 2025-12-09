package org.afterlike.openutils.util.client;

import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class TextUtil {
	private static final @NotNull Minecraft mc = Minecraft.getMinecraft();
	public static @NotNull String replaceColorCodes(@NotNull final String message) {
		return message.replaceAll("&", "§");
	}

	public static @NotNull String stripAliens(@NotNull final String text) {
		StringBuilder sb = new StringBuilder();
		for (char c : text.toCharArray()) {
			if (mc.fontRendererObj.getCharWidth(c) > 0 || c == '§') {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static @NotNull String stripColorCodes(@NotNull final String text) {
		StringBuilder sb = new StringBuilder(text.length());
		boolean skip = false;
		for (char c : text.toCharArray()) {
			if (skip) {
				skip = false;
				continue;
			}
			if (c == '§') {
				skip = true;
				continue;
			}
			sb.append(c);
		}
		return sb.toString();
	}
}
