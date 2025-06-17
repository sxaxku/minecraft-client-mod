package com.dev;

import net.fabricmc.api.ClientModInitializer;

public class Main implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Untitled.newInstance();
    }
}
