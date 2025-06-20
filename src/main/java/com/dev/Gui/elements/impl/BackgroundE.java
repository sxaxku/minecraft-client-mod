package com.dev.Gui.elements.impl;

import com.dev.Gui.elements.AbstractElement;
import net.minecraft.client.gui.DrawContext;

public class BackgroundE extends AbstractElement {
    private final int color;

    public BackgroundE(int color) {
        super();
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    protected boolean isClickable() {
        return false;
    }

    @Override
    protected boolean isDraggable() {
        return false;
    }

    @Override
    protected boolean isScrollable() {
        return false;
    }

    @Override
    public void beforeRender(DrawContext context, int mouseX, int mouseY, float delta) {

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        drawer.drawRect(getColor());
    }

    @Override
    public void afterRender(DrawContext context, int mouseX, int mouseY, float delta) {

    }
}
