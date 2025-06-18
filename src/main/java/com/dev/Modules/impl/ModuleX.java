package com.dev.Modules.impl;
import com.dev.Gui.Elements.ElementManager;
import com.dev.Modules.Module;
import com.dev.Untitled;

import java.awt.*;

public class ModuleX extends Module {

    public ModuleX() {
        super(Category.COMBAT, "moduleX");

        addSetting(ElementManager.createWindow(5, 5, 20, 20, Color.white.getRGB()));
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
