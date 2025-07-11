package com.dev.Modules;

import com.dev.Gui.Elements.MElement;
import com.dev.Gui.Elements.impl.CategoryButton;
import com.dev.Modules.Settings.Setting;
import com.dev.Untitled;
import net.minecraft.client.MinecraftClient;

import java.awt.*;

public abstract class Module {
    private final Category category;
    private final String name;
    private final CategoryButton categoryButton;

    protected final Untitled instance;
    protected final MinecraftClient mc;

    public Module(Category category, String name) {
        this.instance = Untitled.getInstance();
        this.mc = instance.mc;


        this.category = category;
        this.name = name;

        this.categoryButton = new CategoryButton(
                name,
                0, 0, 70, 20,
                new Color(52, 52, 52, 255).getRGB(),
                new Color(60, 60, 60, 255).getRGB(),
                new Color(68, 68, 68, 255).getRGB()
        );

        categoryButton.setRunnableOnToggle(() -> {
            if (categoryButton.isToggled()) {
                Untitled.getInstance().eventManager.register(this);
                onEnable();
            } else {
                Untitled.getInstance().eventManager.unregister(this);
                onDisable();
            }
        });

        switch (category) {
//            case COMBAT -> Untitled.getInstance().guiScreen.combat.addButton(categoryButton);
//            case RENDER -> Untitled.getInstance().guiScreen.render.addButton(categoryButton);
//            case PLAYER -> Untitled.getInstance().guiScreen.player.addButton(categoryButton);
            default -> throw new RuntimeException("Unknown category: " + category.toString());
        }
    }

    protected abstract void onEnable();
    protected abstract void onDisable();

    public final void addSetting(Setting setting) {
        categoryButton.addSetting(setting.getGuiSettingElement());
    }

    public final Category getCategory() {
        return this.category;
    }

    public final String getName() {
        return this.name;
    }

    public enum Category {
        COMBAT, RENDER, PLAYER;

        @Override
        public String toString() {
            String name = name().toLowerCase();
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }
}
