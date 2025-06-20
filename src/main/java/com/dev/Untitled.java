package com.dev;

import com.dev.Event.EventManager;
import com.dev.Event.Subscribe;
import com.dev.Event.impl.ClientTickEvent;
import com.dev.Event.impl.KeyPressEvent;
import com.dev.Gui.GuiManager;
import com.dev.Gui.MainScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Untitled {
    public String MOD_ID = "untitled";
    public Logger LOGGER;

    public ResourceFactory resourceFactory;
    public EventManager eventManager;

    public Drawer drawer;

    public MinecraftClient mc;

    public MainScreen guiScreen;



    private static Untitled instance;
    public static void newInstance() {
        instance = new Untitled();
    }

    public static Untitled getInstance() {
        return instance;
    }

    private Untitled() {
        LOGGER = LoggerFactory.getLogger(this.getClass().getName());

        eventManager = new EventManager();
        eventManager.register(this);

        Drawer.newInstance();
        GuiManager.newInstance();

        mc = MinecraftClient.getInstance();
        drawer = Drawer.getInstance();
        guiScreen = null;

        resourceFactory = new MResourceFactory();
    }

    public Identifier getResource(String path) {
        try {
            return new Identifier(MOD_ID, path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Subscribe
    public void onKeyPress(KeyPressEvent e) {

        if (e.getAction() == GLFW.GLFW_PRESS && e.getKey() == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            LOGGER.info("Pressed right shift !");

            if (guiScreen.isVisible()) guiScreen.close();
            else mc.setScreen(guiScreen);
        }
    }

    @Subscribe
    public void onTick(ClientTickEvent e) {
        if (mc.getWindow() != null && guiScreen == null) {
            guiScreen = new MainScreen();
            guiScreen.Initialize();
            guiScreen.UpdateElements();
        }
    }

}
