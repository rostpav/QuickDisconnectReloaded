package com.rostpav79.quickdisconnectreloaded;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class QuickDisconnectReloaded implements ClientModInitializer {
    public static final String MODID = "quickdisconnectreloaded";

    public static final KeyMapping.Category KEY_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MODID, "keys"));

    public static final KeyMapping DISCONNECT_KEY = new KeyMapping(
            "key.quickdisconnectreloaded.disconnect",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_F10,
            KEY_CATEGORY);

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(DISCONNECT_KEY);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (DISCONNECT_KEY.consumeClick()) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.level != null) {
                    boolean isLocal = mc.isLocalServer();
                    mc.level.disconnect(ClientLevel.DEFAULT_QUIT_MESSAGE);
                    if (isLocal) {
                        mc.disconnectWithSavingScreen();
                    } else {
                        mc.disconnectWithProgressScreen();
                    }
                    mc.setScreen(new TitleScreen());
                }
            }
        });
    }
}
