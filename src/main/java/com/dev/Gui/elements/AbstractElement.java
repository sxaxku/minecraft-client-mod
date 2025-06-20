package com.dev.Gui.elements;

import com.dev.Drawer;
import com.dev.Untitled;
import net.minecraft.client.MinecraftClient;

public abstract class AbstractElement implements IElement, IDrawable, IWidget {
    private int x, y, width, height;
    private boolean dragging, visible, active;

    protected final Untitled instance;
    protected final MinecraftClient mc;
    protected final Drawer drawer;

    protected AbstractElement() {
        this.x = -1;
        this.y = -1;
        this.width = -1;
        this.height = -1;

        this.dragging = false;
        this.visible = true;
        this.active = true;

        this.instance = Untitled.getInstance();
        this.mc = MinecraftClient.getInstance();
        this.drawer = Drawer.getInstance();

        instance.guiScreen.addElement(this);
    }

    protected AbstractElement(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.dragging = false;
        this.visible = true;
        this.active = true;

        this.instance = Untitled.getInstance();
        this.mc = MinecraftClient.getInstance();
        this.drawer = Drawer.getInstance();

        instance.guiScreen.addElement(this);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= this.x &&
                mouseX <= this.x + this.width &&
                mouseY >= this.y &&
                mouseY <= this.y + this.height;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IWidget> T setPos(int x, int y) {
        this.x = x;
        this.y = y;

        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IWidget> T setSize(int width, int height) {
        this.width = width;
        this.height = height;

        return (T) this;
    }

    @Override
    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    @Override
    public boolean isDragging() {
        return this.dragging;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean bl = isMouseOver(mouseX, mouseY);
        if (button == 0 && bl) {
            // playing sound on click... soon
            setDragging(true);
            onClick(mouseX, mouseY, button);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            setDragging(false);
            onRelease(mouseX, mouseY, button);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (isDragging() && button == 0) {
            onDrag(mouseX, mouseY, button, deltaX, deltaY);
            return true;
        }
        return false;
    }


    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (isMouseOver(mouseX, mouseY)) {
            onScroll(mouseX, mouseY, amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    public boolean canDraw() {
        return x != -1 && y != -1 && width != -1 && height != -1;
    }

    protected void onClick(double mouseX, double mouseY, int button) {};
    protected void onRelease(double mouseX, double mouseY, int button) {}
    protected void onDrag(double mouseX, double mouseY, int button, double deltaX, double deltaY) {}
    protected void onScroll(double mouseX, double mouseY, double amount) {};

    protected abstract boolean isClickable();
    protected abstract boolean isDraggable();
    protected abstract boolean isScrollable();
}
