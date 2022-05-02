package fr.seynox.charming.client;

import fr.seynox.charming.network.CharmsInventoryPacketHandler;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

import java.util.Map;

import static fr.seynox.charming.CharmingMod.LOGGER;
import static net.minecraft.client.util.InputUtil.GLFW_KEY_G;

public class KeyBinds {

    private KeyBinds() {}

    private static Map<KeyBinding, Runnable> keyBindingAndActionMap;

    public static void registerKeyBindings() {
        LOGGER.info("Registering keybindings");

        keyBindingAndActionMap = Map.of(
                KeyBindingFactory.createAndRegister("inventory", GLFW_KEY_G, "menu"), CharmsInventoryPacketHandler::sendOpenCharmsInventoryPacket
        );

        ClientTickEvents.END_CLIENT_TICK.register(KeyBinds::onKeyPress);
    }

    private static void onKeyPress(MinecraftClient client) {

        keyBindingAndActionMap.forEach(
                (keybinding, action) -> { // Execute action if key is pressed
                    if(keybinding.isPressed()) {
                        action.run();
                    }
                }
        );

    }

}
