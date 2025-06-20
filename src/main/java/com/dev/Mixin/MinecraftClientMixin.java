package com.dev.Mixin;

import com.dev.Event.impl.ClientTickEvent;
import com.dev.Untitled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("TAIL"), method = "tick")
    private void onTickEnd(CallbackInfo ci) {
        ClientTickEvent event = new ClientTickEvent(); // Событие в конце тика
        Untitled.getInstance().eventManager.trigger(event);
    }
}