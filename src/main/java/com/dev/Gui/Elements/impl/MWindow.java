package com.dev.Gui.Elements.impl;

import com.dev.Drawer;
import com.dev.Gui.Elements.MElement;
import com.dev.Untitled;
import net.minecraft.client.gui.DrawContext;

public class MWindow implements MElement {
    private int x, y, width, height, color;


    @Override
    public <T extends MElement> T copy() {
        MWindow copied = new MWindow(x, y, width, height, color);

        return (T) copied;
    }

     public MWindow(int x, int y, int width, int height, int color) {

         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
         this.color = color;
     }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
         Drawer drawer = Drawer.getInstance();

         drawer.drawRect(color);
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
