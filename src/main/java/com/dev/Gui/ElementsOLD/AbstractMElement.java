package com.dev.Gui.Elements;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.ParentElement;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public abstract class AbstractMElement implements ParentElement, MElement {
    protected final List<MElement> children = Lists.newArrayList();
    private boolean isVisible;
    private boolean dragging;

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return (isVisible && ParentElement.super.mouseClicked(mouseX, mouseY, button));
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return isVisible && ParentElement.super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return isVisible && ParentElement.super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return isVisible && ParentElement.super.mouseScrolled(mouseX, mouseY, amount);
    }


    @Override
    public boolean isDragging() {
        return this.dragging;
    }

    @Override
    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public List<? extends Element> children() {
        return this.children;
    }
}
