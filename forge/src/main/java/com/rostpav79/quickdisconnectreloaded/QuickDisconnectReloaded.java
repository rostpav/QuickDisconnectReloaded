package com.rostpav79.quickdisconnectreloaded;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.Identifier;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod(QuickDisconnectReloaded.MODID)
public class QuickDisconnectReloaded {
    public static final String MODID = "quickdisconnectreloaded";

    public static final KeyMapping.Category KEY_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MODID, "keys"));

    public static final KeyMapping DISCONNECT_KEY = new KeyMapping(
            "key.quickdisconnectreloaded.disconnect",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_F10,
            KEY_CATEGORY);

    public QuickDisconnectReloaded() {
        RegisterKeyMappingsEvent.BUS.addListener(QuickDisconnectReloaded::onKeyRegister);
        TickEvent.ClientTickEvent.Post.BUS.addListener(QuickDisconnectReloaded::onClientTick);
    }

    private static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(DISCONNECT_KEY);
    }

    private static void onClientTick(TickEvent.ClientTickEvent.Post event) {
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
    }
}
