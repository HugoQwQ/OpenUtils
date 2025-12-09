package org.afterlike.openutils.gui.component.impl;

import java.awt.*;
import org.afterlike.openutils.gui.component.Component;
import org.afterlike.openutils.module.api.setting.impl.DescriptionSetting;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class DescriptionComponent extends Component {
	private final int textColor = new Color(226, 83, 47).getRGB();
	private final @NotNull DescriptionSetting description;
	private final @NotNull ModuleComponent parent;
	private int yOffset;
	public DescriptionComponent(@NotNull final DescriptionSetting desc,
			@NotNull final ModuleComponent parent, final int yOffset) {
		this.description = desc;
		this.parent = parent;
		this.yOffset = yOffset;
	}

	@Override
	// TODO: add wrapping
	public void render() {
		GL11.glPushMatrix();
		GL11.glScaled(0.5, 0.5, 0.5);
		mc.fontRendererObj.drawString(this.description.getValue(),
				(this.parent.panel.getX() + 4) * 2,
				(this.parent.panel.getY() + this.yOffset + 4) * 2, this.textColor, true);
		GL11.glPopMatrix();
	}

	@Override
	public void setOffset(final int yOffset) {
		this.yOffset = yOffset;
	}
}
