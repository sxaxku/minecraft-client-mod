package com.dev.Mixin;

import com.dev.Event.impl.KeyPressEvent;
import com.dev.Untitled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Keyboard.class)
class KeyBoardMixin {
    @Inject(at = @At("TAIL"), method = "onKey")
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        KeyPressEvent event = new KeyPressEvent(key, scancode, action, modifiers);
        Untitled.getInstance().eventManager.trigger(event); // Предполагается, что EventManager доступен

        if (event.isCancelled()) ci.cancel();
    }
}