package com.dev.Gui.Elements.impl;

import com.dev.Drawer;
import com.dev.Gui.Elements.AbstractMElement;
import com.dev.Gui.Elements.MElement;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import org.jetbrains.annotations.Nullable;

public class ScrolledWindow extends AbstractMElement {
    private int x, y, width, height, elementHeight, color;
    private float scrollOffset;
    private float targetScrollOffset;
    private final float scrollSpeed = 0.2f; // Увеличил для чуть более быстрой реакции
    private final float scrollSensitivity = 1f; // Чувствительность прокрутки

    @Override
    public <T extends MElement> T copy() {
        ScrolledWindow copied = new ScrolledWindow(x, y, width, height, elementHeight, color);

        for (MElement element : children) {
            copied.children.add(element.copy());
        }

        return (T) copied;
    }

    public ScrolledWindow(int x, int y, int width, int height, int elementHeight, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.elementHeight = elementHeight;
        this.color = color;
        this.scrollOffset = 0;
        this.targetScrollOffset = 0;

        setVisible(true);
    }

    public final void addElement(MElement element) {
        children.add(element);
    }

    public final void clear() {
        children.clear();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Ограничиваем интерполяцию, чтобы избежать дергания
        float maxDelta = 1.0f / 60.0f; // Предполагаем 60 FPS как базовую частоту
        float adjustedDelta = Math.min(delta, maxDelta); // Ограничиваем delta

        // Интерполяция с учетом ограниченного delta
        scrollOffset += (targetScrollOffset - scrollOffset) * scrollSpeed * adjustedDelta * 60;

        // Ограничиваем scrollOffset, чтобы избежать выхода за границы
        int maxScroll = Math.max(0, children.size() - Math.floorDiv(height, elementHeight));
        scrollOffset = Math.max(0, Math.min(scrollOffset, maxScroll));

        Drawer drawer = Drawer.getInstance();
        drawer.drawRect(color);

        int index = 0;
        int maxVisibleElements = Math.floorDiv(height, elementHeight) + 1;

        int startIndex = (int) Math.floor(scrollOffset);
        float fractionalOffset = scrollOffset - startIndex;

        for (int i = startIndex; i < children.size(); i++) {
            if (i < 0 || index > maxVisibleElements) continue;

            MElement element = children.get(i);

            int elementY = (int) (y + (elementHeight * (index - fractionalOffset)) + 1);

            // Пропускаем рендеринг, если элемент вне видимой области
            if (elementY + elementHeight < y || elementY > y + height) {
                index++;
                continue;
            }

            drawer.enableScissor(x, y, width, height);

            int MHeight = elementHeight - 5;

            drawer.setPosition(x + 5, elementY);
            drawer.setSize(width - 10, MHeight);

            element.setPosition(x + 5, elementY);
            element.setSize(width - 10, MHeight);

            element.beforeRenderer(context, mouseX, mouseY, delta);
            element.render(context, mouseX, mouseY, delta);

            drawer.disableScissor();

            index++;
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (isMouseOver(mouseX, mouseY)) {
            //ystem.out.println("Scroll amount: " + amount + ", targetScrollOffset before: " + targetScrollOffset);
            targetScrollOffset += -amount * 1f * scrollSensitivity;
            int maxScroll = Math.max(0, (children.size() - (height / elementHeight)));
            targetScrollOffset = Math.max(0, Math.min(targetScrollOffset, maxScroll));
            System.out.println("targetScrollOffset after: " + targetScrollOffset);
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
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