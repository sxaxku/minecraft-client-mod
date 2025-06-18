package com.dev.Gui.Elements.impl;

import com.dev.Gui.Elements.MElement;

public class Setting {
    private final MElement settingElement;

    public Setting(MElement settingElement) {
        this.settingElement = settingElement;
    }

    public MElement getSettingElement() {
        return settingElement.copy();
    }
}
