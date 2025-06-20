package com.dev.Gui.Elements.impl;

import com.dev.Drawer;
import com.dev.Gui.Elements.MElement;
import com.dev.Untitled;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ToggleButton implements MElement {
    private final String name;
    private int x, y, width, height, color, hoveredColor, toggledColor;
    private boolean toggled;
    private Runnable methodOnClick;

    @Override
    public <T extends MElement> T copy() {
        ToggleButton copied = new ToggleButton(name, x, y, width, height, color, hoveredColor, toggledColor);
        copied.setMethodOnClick(methodOnClick);
        copied.setToggled(toggled);
        return (T) copied;
    }

    public ToggleButton(String name, int x, int y, int width, int height, int color, int hoveredColor, int toggledColor) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.hoveredColor = hoveredColor;
        this.toggledColor = toggledColor;

        this.toggled = false;
    }

    public final void setMethodOnClick(Runnable runnable) {
        this.methodOnClick = runnable;
    }

    public final void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public final boolean isToggled() {
        return this.toggled;
    }

    public final String getName() {
        return name;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        Drawer drawer = Drawer.getInstance();

        drawer.drawRect(
                toggled ? toggledColor :
                        (isMouseOver(mouseX, mouseY) ? hoveredColor : color)
        );

        int defaultTextColor = new Color(192, 192, 192, 255).getRGB();
        int toggledTextColor = Color.white.getRGB();

        int textHeight = Untitled.getInstance().mc.textRenderer.fontHeight;
        // Центрируем текст по высоте
        int textY = y + (height - textHeight) / 2;

        drawer.drawText(name, x + 10, textY, toggled ? toggledTextColor : defaultTextColor);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            setToggled(!toggled);
            if (methodOnClick != null) methodOnClick.run();
            return true;
        }

        return false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
    }

    @Override
    public void setFocused(boolean focused) {

    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
