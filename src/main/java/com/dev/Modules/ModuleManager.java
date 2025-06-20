package com.dev.Modules;

import com.dev.Modules.impl.ModuleX;
import com.dev.Untitled;

public class ModuleManager {
    public static Module moduleX;

    public static void init() {
//        Untitled.getInstance().guiScreen.combat.clear();
//        Untitled.getInstance().guiScreen.render.clear();
//        Untitled.getInstance().guiScreen.player.clear();

        moduleX = new ModuleX();
    }
}
