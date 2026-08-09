package com.rostpav79.quickdisconnectreloaded;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod(QuickDisconnectReloaded.MODID)
public class QuickDisconnectReloaded {
    public static final String MODID = "quickdisconnectreloaded";

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        public static final KeyMapping DISCONNECT_KEY = new KeyMapping(
                "key.quickdisconnectreloaded.disconnect",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_F10,
                "category.quickdisconnectreloaded.keys");

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(DISCONNECT_KEY);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                while (ClientModEvents.DISCONNECT_KEY.consumeClick()) {
                    Minecraft mc = Minecraft.getInstance();
                    if (mc.level != null) {
                        boolean isLocal = mc.isLocalServer();
                        mc.level.disconnect();
                        if (isLocal) {
                            mc.disconnect(new GenericMessageScreen(Component.translatable("menu.savingLevel")));
                        } else {
                            mc.disconnect();
                        }
                        mc.setScreen(new TitleScreen());
                    }
                }
            }
        }
    }
}
