package org.afterlike.openutils.module.impl.movement;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.afterlike.openutils.event.handler.EventHandler;
import org.afterlike.openutils.module.api.Module;
import org.afterlike.openutils.module.api.ModuleCategory;
import org.afterlike.openutils.platform.mixin.minecraft.client.settings.KeyBindingAccessor;
import org.afterlike.openutils.util.client.ClientUtil;
import org.jetbrains.annotations.NotNull;

public class SprintModule extends Module {

    public SprintModule() {
        super("Sprint", ModuleCategory.MOVEMENT);
    }

    @EventHandler
    private void onTick(@NotNull TickEvent event) {
        if (!ClientUtil.notNull()) return;

        ((KeyBindingAccessor) mc.gameSettings.keyBindSprint).ou$setPressed(true);
    }


    @Override
    public void onDisable() {
        if (mc.thePlayer != null) {
            mc.thePlayer.setSprinting(false);
        }
    }
}
