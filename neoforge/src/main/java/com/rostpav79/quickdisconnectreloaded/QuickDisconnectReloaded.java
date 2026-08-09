package com.rostpav79.quickdisconnectreloaded;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@Mod(QuickDisconnectReloaded.MODID)
public class QuickDisconnectReloaded {
    public static final String MODID = "quickdisconnectreloaded";

    public static final KeyMapping.Category KEY_CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath(MODID, "keys"));

    public static final KeyMapping DISCONNECT_KEY = new KeyMapping(
            "key.quickdisconnectreloaded.disconnect",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_F10,
            KEY_CATEGORY);

    public QuickDisconnectReloaded(IEventBus modEventBus) {
        modEventBus.addListener(this::onKeyRegister);
    }

    private void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.registerCategory(KEY_CATEGORY);
        event.register(DISCONNECT_KEY);
    }

    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
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
}
