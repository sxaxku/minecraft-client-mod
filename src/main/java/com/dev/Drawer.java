package com.dev;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Stack;

public class Drawer {
    private static Drawer instance;
    private final Stack<State> stateStack; // Стек для хранения состояний переменных

    private Drawer() {
        stateStack = new Stack<>();
    }

    public static void newInstance() {
        instance = new Drawer();
    }

    public static Drawer getInstance() {
        return instance;
    }

    private boolean settersIsBlocked = false;

    private DrawContext context;
    private Identifier texture;
    private int u, v, textureWidth, textureHeight;
    private int x, y, width, height;

    public final void blockSetters() {
        settersIsBlocked = true;
    }

    public final void unblockSetters() {
        settersIsBlocked = false;
    }

    // Сохранение состояния матрицы и переменных
    public final void push() {
        if (context == null) {
            throw new IllegalStateException("DrawContext is not set. Call setContext() first.");
        }
        // Сохраняем матрицу преобразований
        context.getMatrices().push();
        // Сохраняем состояние переменных
        stateStack.push(new State(x, y, width, height, u, v, textureWidth, textureHeight, texture));
    }

    // Восстановление состояния матрицы и переменных
    public final void pop() {
        if (context == null) {
            throw new IllegalStateException("DrawContext is not set. Call setContext() first.");
        }
        if (stateStack.isEmpty()) {
            throw new IllegalStateException("No states to pop. Call push() before pop().");
        }
        // Восстанавливаем матрицу преобразований
        context.getMatrices().pop();
        // Восстанавливаем состояние переменных
        State state = stateStack.pop();
        this.x = state.x;
        this.y = state.y;
        this.width = state.width;
        this.height = state.height;
        this.u = state.u;
        this.v = state.v;
        this.textureWidth = state.textureWidth;
        this.textureHeight = state.textureHeight;
        this.texture = state.texture;
    }

    public final void setContext(DrawContext context) {
        this.context = context;
    }

    public final DrawContext context() {
        return this.context;
    }

    public final void setTexture(Identifier texture) {
        this.texture = texture;
    }

    public final Identifier getTexture() {
        return this.texture;
    }

    public final int getTextureU() {
        return this.u;
    }

    public final int getTextureV() {
        return this.v;
    }

    public final int getTextureWidth() {
        return this.textureWidth;
    }

    public final int getTextureHeight() {
        return this.textureHeight;
    }

    public final void setTexturePosition(int u, int v) {
        if (settersIsBlocked) return;
        this.u = u;
        this.v = v;
    }

    public final void setTextureSize(int textureWidth, int textureHeight) {
        if (settersIsBlocked) return;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setPosition(int x, int y) {
        if (settersIsBlocked) return;
        this.x = x;
        this.y = y;
    }

    public final void setSize(int width, int height) {
        if (settersIsBlocked) return;
        this.width = width;
        this.height = height;
    }

    public final void disableScissor() {
        context.disableScissor();
    }

    public final void enableScissor(int x, int y, int width, int height) {
        context.enableScissor(x, y, x + width, y + height);
    }

    public final void drawRect(int color) {
        context.fill(x, y, x + width, y + height, color);
    }

    public final void drawBorder(int color) {
        context.drawBorder(x, y, width, height, color);
    }

    public final void drawTexture() {
        context.drawTexture(this.texture, this.x, this.y, this.u, this.v, this.textureWidth, this.textureHeight);
    }

    public final void drawTexture(int u, int v) {
        setTexturePosition(u, v);
        drawTexture();
    }

    public final void drawText(String str, int x, int y, int color) {
        Text text = Text.literal(str).setStyle(Style.EMPTY.withFont(Untitled.getInstance().getResource("default")));
        context.drawText(Untitled.getInstance().mc.textRenderer, text, x, y, color, false);
    }

    // Класс для хранения состояния переменных
    private static class State {
        final int x, y, width, height;
        final int u, v, textureWidth, textureHeight;
        final Identifier texture;

        State(int x, int y, int width, int height, int u, int v, int textureWidth, int textureHeight, Identifier texture) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.u = u;
            this.v = v;
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
            this.texture = texture;
        }
    }
}