package com.dev.Gui.elements;

import com.dev.Gui.elements.impl.BackgroundE;

public final class EZElement {
    public static BackgroundE background(int x, int y, int width, int height, int color) {
        return new BackgroundE(color).setPos(x, y).setSize(width, height);
    }
}
