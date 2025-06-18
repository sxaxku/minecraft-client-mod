package com.dev.Gui;

import com.dev.Drawer;
import com.dev.Gui.Elements.ElementManager;
import com.dev.Gui.Elements.MElement;
import com.dev.Gui.Elements.impl.*;
import com.dev.Modules.ModuleManager;
import com.dev.Untitled;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.apache.commons.compress.utils.Lists;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainScreen extends Screen {
    private final CopyOnWriteArrayList<MElement> children = new CopyOnWriteArrayList<>();
    private boolean isVisible;
    private final Drawer drawer;
    private Integer xScreen, yScreen, widthScreen, heightScreen;

    private List<Category> categories = Lists.newArrayList();
    public final Category combat = new Category("Combat");
    public final Category render = new Category("Render");
    public final Category player = new Category("Player");
    private Category selectedCategory;
    private CategoryButton selectedCategoryButton;


    public MainScreen() {
        super(Text.of("Gui"));
        drawer = Drawer.getInstance();

        categories.addAll(List.of(
                combat, render, player
        ));

        selectedCategory = null; // Изначально категория не выбрана
    }

    public final boolean isVisible() {
        return this.isVisible;
    }

    public final Drawer getDrawer() {
        return this.drawer;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public CategoryButton getSelectedCategoryButton() {
        return selectedCategoryButton;
    }

    public void methodForTestRunnable1() {

    }

    public void methodForTestRunnable2() {

    }

    public void methodForTestRunnable3() {

    }

    public void methodForTestRunnable4() {

    }

    @Override
    public void onDisplayed() {
        init();
        children.clear();
        ModuleManager.init();

        int backgroundColor = new Color(0, 0, 0, 32).getRGB();
        int guiBackgroundColor = new Color(32, 32, 32, 255).getRGB();

        this.add(ElementManager.createWindow(0, 0, super.width, super.height, backgroundColor));
        this.add(ElementManager.createWindow(xScreen, yScreen, widthScreen, heightScreen, guiBackgroundColor));

        ScrolledWindow scrolledWindow = new ScrolledWindow(
                xScreen + 5, yScreen + 5,
                80, heightScreen - 60, 20,
                new Color(44, 44, 44, 255).getRGB()
        );

        for (Category category : categories) {
            ToggleButton categoryButton = ElementManager.createToggleButton(
                    category.getName(),
                    xScreen + 5,
                    yScreen + 5,
                    80, 20,
                    new Color(52, 52, 52, 255).getRGB(),
                    new Color(60, 60, 60, 255).getRGB(),
                    new Color(68, 68, 68, 255).getRGB()
            );

            if (getSelectedCategory() != null && Objects.equals(getSelectedCategory().getName(), category.getName())) {
                categoryButton.setToggled(true);
            }

            category.setButton(categoryButton);

            categoryButton.setMethodOnClick(() -> {
                if (getSelectedCategory() != null) {
                    getSelectedCategory().setSelected(false);
                    getSelectedCategory().getButton().setToggled(false);
                    if (getSelectedCategory() == category) {
                        selectedCategory = null;

                        onDisplayed();
                        return;
                    }
                }

                selectedCategoryButton = null;
                selectedCategory = category;
                selectedCategory.setSelected(true);
                selectedCategory.getButton().setToggled(true);

                onDisplayed();
            });

            scrolledWindow.addElement(categoryButton);
        }

        this.add(scrolledWindow);

        if (getSelectedCategory() != null) {
            ScrolledWindow categoryScrolledWindow = new ScrolledWindow(
                    xScreen + 5 + 80 + 5, yScreen + 5,
                    80, heightScreen - 60, 20,
                    new Color(44, 44, 44, 255).getRGB()
            );

            for (CategoryButton button : getSelectedCategory().getButtons()) {
                categoryScrolledWindow.addElement(button);
                button.setRunnableOnSettingToggle(() -> {

                    if (selectedCategoryButton == null) {
                        selectedCategoryButton = button;
                        button.setSettingsOpenStatus(true);
                    } else {
                        if (Objects.equals(selectedCategoryButton.getName(), button.getName())) {
                            button.setSettingsOpenStatus(false);
                            selectedCategoryButton = null;
                        } else {
                            selectedCategoryButton.setSettingsOpenStatus(false);
                            selectedCategoryButton = button;
                        }
                    }

                    onDisplayed();
                });
            }

            this.add(categoryScrolledWindow);

            if (getSelectedCategoryButton() != null && getSelectedCategoryButton().settingsIsOpened()) {
                MWindow settingWindow = ElementManager.createWindow(
                        xScreen + 5, yScreen + heightScreen - 50,
                        165, 45,
                        new Color(44, 44, 44, 255).getRGB()
                );

                this.add(settingWindow);

                for (Setting elementX : getSelectedCategoryButton().getSettings()) {
                    MElement element = elementX.getSettingElement();
                    int elX = element.getX();
                    int elY = element.getY();
                    element.setPosition(element.getX() + xScreen + 5, element.getY() + yScreen + heightScreen - 50);

                    this.add(element);
                }
            }
        }

        isVisible = true;
    }

    @Override
    public void close() {
        Untitled.getInstance().mc.setScreen(null);

        isVisible = false;
        children.clear();
    }

    public final void add(MElement element) {
        children.add(element);
    }

    @Override
    protected void init() {
        float baseWidth = 300;
        float baseHeight = 200;
        int screenWidth = Untitled.getInstance().mc.getWindow().getScaledWidth();
        int screenHeight = Untitled.getInstance().mc.getWindow().getScaledHeight();
        float scaleFactor = Math.min(screenWidth / 854f, screenHeight / 480f);
        int width = Math.round(baseWidth * scaleFactor);
        int height = Math.round(baseHeight * scaleFactor);
        int x = (screenWidth - width) / 2;
        int y = (screenHeight - height) / 2;

        xScreen = x;
        yScreen = y;
        widthScreen = width;
        heightScreen = height;

        drawer.setPosition(xScreen, yScreen);
        drawer.setSize(widthScreen, heightScreen);
    }

    @Override
    public void renderBackground(DrawContext context) {
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        drawer.setContext(context);

        for (MElement element : children) {
            drawer.push();

            drawer.setPosition(element.getX(), element.getY());
            drawer.setSize(element.getWidth(), element.getHeight());

            element.beforeRenderer(context, mouseX, mouseY, delta);
            element.render(context, mouseX, mouseY, delta);

            drawer.pop();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return (isVisible && super.mouseClicked(mouseX, mouseY, button));
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return isVisible && super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return isVisible && super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return isVisible && super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public void tick() {
        if (!isVisible) return;
        super.tick();
    }

    @Override
    public CopyOnWriteArrayList<? extends Element> children() {
        return this.children;
    }
}