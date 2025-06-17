package com.dev.Gui.Elements;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;

public interface MElement extends Element, Drawable {
    default void MInitialize() {}

    int getX();
    int getY();
    int getWidth();
    int getHeight();

    void setPosition(int x, int y);
    void setSize(int width, int height);
}
