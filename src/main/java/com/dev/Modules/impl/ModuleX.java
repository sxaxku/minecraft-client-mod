package com.dev.Modules.impl;
import com.dev.Event.Subscribe;
import com.dev.Event.impl.KeyPressEvent;
import com.dev.Gui.Elements.ElementManager;
import com.dev.Modules.Module;
import com.dev.Modules.Settings.impl.ButtonSetting;
import com.dev.Untitled;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class ModuleX extends Module {
    private ButtonSetting buttonSetting;

    public ModuleX() {
        super(Category.COMBAT, "moduleX");

        buttonSetting = new ButtonSetting("Setting", 5, 5, 20, 20);

        addSetting(buttonSetting);
    }

    @Subscribe
    public final void onKeyPressEvent(KeyPressEvent event) {
        // (event.getKey() == GLFW.GLFW_KEY_CAPS_LOCK) {
            instance.LOGGER.info(mc.player.getDisplayName().getString() + " press: " + GLFW.glfwGetKeyName(event.getKey(), event.getScancode()));
        //}
    }

    @Override
    protected void onEnable() {
        Untitled.getInstance().LOGGER.info("enabled");
    }

    @Override
    protected void onDisable() {
        Untitled.getInstance().LOGGER.info("disable");
    }
}
