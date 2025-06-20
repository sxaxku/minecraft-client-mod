package com.dev.Modules.Settings;

public abstract class AbstractSetting implements Setting {
    private final String name;
    private final int x, y, width, height;

    protected AbstractSetting(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected final String getName() {
        return this.name;
    }

    protected final int getX() {
        return x;
    }

    protected final int getY() {
        return y;
    }

    protected final int getWidth() {
        return width;
    }

    protected final int getHeight() {
        return height;
    }
}
