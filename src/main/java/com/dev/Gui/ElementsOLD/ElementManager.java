package com.dev.Gui.Elements;

import com.dev.Gui.Elements.impl.MWindow;
import com.dev.Gui.Elements.impl.ToggleButton;

public class ElementManager {
    public static MWindow createWindow(int x, int y, int width, int height, int color) {
        return new MWindow(x, y, width, height, color);
    }

    public static ToggleButton createToggleButton(String name, int x, int y, int width, int height, int color, int hoveredColor, int toggledColor) {
        return new ToggleButton(name, x, y, width, height, color, hoveredColor, toggledColor);
    }
}