package com.dev.Gui;

import com.dev.Drawer;
import com.dev.Gui.Elements.ElementManager;
import com.dev.Gui.Elements.MElement;
import com.dev.Gui.Elements.impl.ScrolledWindow;
import com.dev.Gui.Elements.impl.ToggleButton;
import com.dev.Untitled;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.apache.commons.compress.utils.Lists;

import java.awt.*;
import java.util.List;

public class MainScreen extends Screen {
    private final List<MElement> children = Lists.newArrayList();
    private boolean isVisible;
    private final Drawer drawer;
    private Integer xScreen, yScreen, widthScreen, heightScreen;
    private String selectedCategory; // Переменная для хранения выбранной категории
    private final List<ToggleButton> categoryButtons = Lists.newArrayList(); // Список кнопок категорий

    public MainScreen() {
        super(Text.of("Gui"));
        drawer = Drawer.getInstance();
        selectedCategory = null; // Изначально категория не выбрана
    }

    public final boolean isVisible() {
        return this.isVisible;
    }

    public final Drawer getDrawer() {
        return this.drawer;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    @Override
    public void onDisplayed() {
        init();

        int backgroundColor = new Color(0, 0, 0, 32).getRGB();
        int guiBackgroundColor = new Color(32, 32, 32, 255).getRGB();

        this.add(ElementManager.createWindow(0, 0, super.width, super.height, backgroundColor));
        this.add(ElementManager.createWindow(xScreen, yScreen, widthScreen, heightScreen, guiBackgroundColor));

        ScrolledWindow scrolledWindow = new ScrolledWindow(
                xScreen + 5, yScreen + 5,
                80, heightScreen - 10, 20,
                new Color(44, 44, 44, 255).getRGB()
        );

        this.add(scrolledWindow);

        // Создаем кнопки для категорий
        String[] categories = {"Combat", "Render", "Player"};
        for (String category : categories) {
            ToggleButton categoryButton = ElementManager.createToggleButton(
                    category,
                    xScreen + 5,
                    yScreen + 5,
                    80, 20,
                    new Color(52, 52, 52, 255).getRGB(),
                    new Color(60, 60, 60, 255).getRGB(),
                    new Color(68, 68, 68, 255).getRGB()
            );

            // Добавляем обработчик клика
            categoryButton.setMethodOnClick(() -> {
                // Если эта кнопка уже выбрана, снимаем выбор
                if (selectedCategory != null && selectedCategory.equals(category)) {
                    categoryButton.setToggled(false);
                    selectedCategory = null;
                } else {
                    // Отключаем все другие кнопки
                    for (ToggleButton btn : categoryButtons) {
                        if (btn != categoryButton) {
                            btn.setToggled(false);
                        }
                    }
                    // Устанавливаем текущую категорию
                    categoryButton.setToggled(true);
                    selectedCategory = category;
                }
            });

            categoryButtons.add(categoryButton);
            scrolledWindow.addElement(categoryButton);
        }



        scrolledWindow =  new ScrolledWindow(
                xScreen + 5 + 80 + 5, yScreen + 5,
                80, 40, 20,
                new Color(44, 44, 44, 255).getRGB()
        );

        for(int i = 0; i <= 0; i++) {
            ToggleButton tb = ElementManager.createToggleButton(
                    "Button" + i,
                    xScreen + 5,
                    yScreen + 5,
                    80, 20,
                    new Color(52, 52, 52, 255).getRGB(),
                    new Color(60, 60, 60, 255).getRGB(),
                    new Color(68, 68, 68, 255).getRGB()
            );

            tb.setMethodOnClick(() -> {

            });

            scrolledWindow.addElement(tb);
        }


        isVisible = true;
    }

    @Override
    public void close() {
        Untitled.getInstance().mc.setScreen(null);

        isVisible = false;
        children.clear();
        categoryButtons.clear();
        selectedCategory = null;
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
    public List<? extends Element> children() {
        return this.children;
    }
}