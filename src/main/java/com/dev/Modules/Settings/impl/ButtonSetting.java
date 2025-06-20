package com.dev.Modules.Settings.impl;

import com.dev.Gui.Elements.ElementManager;
import com.dev.Gui.Elements.MElement;
import com.dev.Gui.Elements.impl.ToggleButton;
import com.dev.Modules.Settings.AbstractSetting;

import java.awt.*;

public class ButtonSetting extends AbstractSetting {
    private final MElement element;
    private boolean status;

    public ButtonSetting(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);

        this.element = ElementManager.createToggleButton(name, x, y, width, height,
                new Color(52, 52, 52, 255).getRGB(),
                new Color(60, 60, 60, 255).getRGB(),
                new Color(68, 68, 68, 255).getRGB()
        );
        this.status = false;

        ((ToggleButton) element).setMethodOnClick(this::onPressButton);
    }

    private void onPressButton() {
        status = !status;
        onChangeStatus(this.status);
    }

    protected void onChangeStatus(boolean status) {
        // ---------------------------
    }

    public final boolean getStatus() {
        return this.status;
    }

    @Override
    public MElement getGuiSettingElement() {
        return this.element;
    }
}
