package com.dev.Gui.Elements.impl;

import com.dev.Drawer;
import com.dev.Gui.Elements.AbstractMElement;
import com.dev.Gui.Elements.MElement;
import com.dev.Untitled;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.List;

public class CategoryButton extends AbstractMElement {
    private final String name;
    private int x, y, width, height, color, hoveredColor, toggledColor;
    private boolean toggled, settingsIsOpened;
    private Runnable onToggling, onSettingsToggling;

    private final List<MElement> settings;

    @Override
    public <T extends MElement> T copy() {
        CategoryButton copied = new CategoryButton(name, x, y, width, height, color, hoveredColor, toggledColor);
        copied.setRunnableOnToggle(onToggling);
        copied.setRunnableOnSettingToggle(onSettingsToggling);
        for (MElement setting : settings) {
            copied.addSetting(setting);
        }
        return (T) copied;
    }

    public CategoryButton(String name, int x, int y, int width, int height, int color, int hoveredColor, int toggledColor) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.hoveredColor = hoveredColor;
        this.toggledColor = toggledColor;

        this.settings = Lists.newArrayList();
        this.toggled = false;
        this.settingsIsOpened = false;
    }

        public final void addSetting(MElement element) {
        settings.add(element);
    }

        public final List<MElement> getSettings() {
        return this.settings;
    }

    public final boolean settingsIsOpened() {
        return this.settingsIsOpened;
    }

    public final void setSettingsOpenStatus(boolean settingsIsOpened) {
        this.settingsIsOpened = settingsIsOpened;
    }

    public final void setRunnableOnToggle(Runnable runnable) {
        this.onToggling = runnable;
    }

    public final void setRunnableOnSettingToggle(Runnable runnable) {
        this.onSettingsToggling = runnable;
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
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                this.toggled = !this.toggled;
                if (onToggling != null) onToggling.run();
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                this.settingsIsOpened = !this.settingsIsOpened;
                if (onSettingsToggling != null) onSettingsToggling.run();
            }

            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public @Nullable Element getFocused() {
        return null;
    }

    @Override
    public void setFocused(@Nullable Element focused) {

    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
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
