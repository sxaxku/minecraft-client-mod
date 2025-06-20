package com.dev.Gui.elements;

public interface IWidget {
    boolean isVisible();
    void setVisible(boolean visible);

    int getX();
    int getY();
    int getWidth();
    int getHeight();

    <T extends IWidget> T setPos(int x, int y);
    <T extends IWidget> T setSize(int width, int height);

    boolean isActive();
    void setActive(boolean active);
}
