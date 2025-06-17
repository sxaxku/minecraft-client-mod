package com.dev.Event.impl;


import com.dev.Event.Event;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Класс события
public class KeyPressEvent extends Event {
    private final int key;
    private final int scancode;
    private final int action;
    private final int modifiers;

    private boolean cancelled;

    public KeyPressEvent(int key, int scancode, int action, int modifiers) {
        this.key = key;
        this.scancode = scancode;
        this.action = action;
        this.modifiers = modifiers;
    }


    // Методы для проверки модификаторов
    public boolean isShiftPressed() {
        return (modifiers & GLFW.GLFW_MOD_SHIFT) != 0;
    }

    public boolean isControlPressed() {
        return (modifiers & GLFW.GLFW_MOD_CONTROL) != 0;
    }

    public boolean isAltPressed() {
        return (modifiers & GLFW.GLFW_MOD_ALT) != 0;
    }

    public boolean isSuperPressed() {
        return (modifiers & GLFW.GLFW_MOD_SUPER) != 0;
    }

    public boolean isCapsLockActive() {
        return (modifiers & GLFW.GLFW_MOD_CAPS_LOCK) != 0;
    }

    public boolean isNumLockActive() {
        return (modifiers & GLFW.GLFW_MOD_NUM_LOCK) != 0;
    }

    // Проверка, нажат ли хотя бы один модификатор
    public boolean isAnyModifierPressed() {
        return modifiers != 0; // Возвращает true, если есть любой ненулевой модификатор
    }

    // Геттеры для остальных полей
    public int getKey() {
        return key;
    }

    public int getScancode() {
        return scancode;
    }

    public int getAction() {
        return action;
    }

    public int getModifiers() {
        return modifiers;
    }

    // Методы для управления отменой события
    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }
}