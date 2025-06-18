package com.dev.Gui.Elements.impl;

import com.dev.Gui.Elements.MElement;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class Category {
    private final String name;
    private boolean selected;
    private ToggleButton button;

    private final List<CategoryButton> categoryButtons;

    public Category(String name) {
        this.name = name;
        this.categoryButtons = Lists.newArrayList();
    }

    public final void addButton(CategoryButton button) {
        categoryButtons.add(button);
    }

    public final List<CategoryButton> getButtons() {
        return categoryButtons;
    }

    public final void clear() {
        categoryButtons.clear();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ToggleButton getButton() {
        return button;
    }

    public void setButton(ToggleButton toggleButton) {
        this.button = toggleButton;
    }

    public String getName() {
        return name;
    }
}
