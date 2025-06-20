package com.dev.Gui;

import com.dev.Gui.Elements.MElement;
import com.dev.Gui.elements.AbstractElement;
import com.dev.Gui.elements.EZElement;
import com.dev.Untitled;
import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.List;

public final class MainScreen extends Screen {
    private boolean isVisible;

    private Untitled instance;
    private MinecraftClient mc;

    private List<AbstractElement> elements;

    private int xScreen, yScreen, widthScreen, heightScreen;

    public MainScreen() {
        super(Text.of(""));
    }

    public void addElement(AbstractElement element) {
        elements.add(element);
    }

    public void clear() {
        elements.clear();
    }

    public void Initialize() {
        instance = Untitled.getInstance();
        mc = MinecraftClient.getInstance();

        elements = Lists.newArrayList();

        float baseWidth = 300;
        float baseHeight = 200;
        int screenWidth = mc.getWindow().getScaledWidth();
        int screenHeight = mc.getWindow().getScaledHeight();
        float scaleFactor = Math.min(screenWidth / 854f, screenHeight / 480f);
        int width = Math.round(baseWidth * scaleFactor);
        int height = Math.round(baseHeight * scaleFactor);
        int x = (screenWidth - width) / 2;
        int y = (screenHeight - height) / 2;

        xScreen = x;
        yScreen = y;
        widthScreen = width;
        heightScreen = height;
    }

    public void UpdateElements() {
        Initialize();

        EZElement.background(xScreen, yScreen, widthScreen, heightScreen, new Color(50, 50, 50, 255).getRGB());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        instance.drawer.setContext(context);
        instance.drawer.setPosition(0, 0);
        instance.drawer.setSize(width, height);
        instance.drawer.push();
        for (AbstractElement element : elements) {
            if (!element.canDraw()) {
                instance.LOGGER.warn("Element " + element.toString() + " don't drawn !");
                //System.out.println(element.getX() != -1 && element.getY() != -1 && element.getWidth() != -1 && element.getHeight() != -1);
                continue;
            } else if (!element.isVisible()) continue;

            instance.drawer.setPosition(element.getX(), element.getY());
            instance.drawer.setSize(element.getWidth(), element.getHeight());

            element.beforeRender(context, mouseX, mouseY, delta);
            element.render(context, mouseX, mouseY, delta);
            element.afterRender(context, mouseX, mouseY, delta);
        }
        instance.drawer.pop();
    }

    @Override
    public void onDisplayed() {
        UpdateElements();
        isVisible = true;
    }

    @Override
    public void close() {
        isVisible = false;
        mc.setScreen(null);
    }

    public boolean isVisible() {
        return this.isVisible;
    }



    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (AbstractElement element : elements) {
            if (element.mouseClicked(mouseX, mouseY, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (AbstractElement element : elements) {
            if (element.mouseReleased(mouseX, mouseY, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        for (AbstractElement element : elements) {
            if (element.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        for (AbstractElement element : elements) {
            if (element.mouseScrolled(mouseX, mouseY, amount)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.close();
            return true;
        }
        for (AbstractElement element : elements) {
            if (element.keyPressed(keyCode, scanCode, modifiers)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        for (AbstractElement element : elements) {
            if (element.keyReleased(keyCode, scanCode, modifiers)) {
                return true;
            }
        }
        return false;
    }

}
