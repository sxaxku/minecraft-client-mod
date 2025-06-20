package com.dev.Gui.elements;

import net.minecraft.client.gui.DrawContext;

import java.util.List;
import java.util.Optional;

public interface IElement {

    void setDragging(boolean dragging);
    boolean isDragging();

    default boolean isMouseOver(double mouseX, double mouseY) {
        return false;
    }

    default boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    default boolean mouseReleased(double mouseX, double mouseY, int button) {
        return false;
    }

    default boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return false;
    }

    default boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return false;
    }

    default boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    default boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return false;
    }
}
