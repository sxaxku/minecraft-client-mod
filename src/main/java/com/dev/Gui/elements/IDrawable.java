package com.dev.Gui.elements;

import net.minecraft.client.gui.DrawContext;

public interface IDrawable {
    void beforeRender(DrawContext context, int mouseX, int mouseY, float delta);
    void render(DrawContext context, int mouseX, int mouseY, float delta);
    void afterRender(DrawContext context, int mouseX, int mouseY, float delta);

}
