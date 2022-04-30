package fr.seynox.charming;

import fr.seynox.charming.client.KeyBinds;
import fr.seynox.charming.inventory.gui.CharmsInventoryScreen;
import fr.seynox.charming.inventory.gui.CharmsScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class CharmingClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBinds.registerKeyBindings();
        initializeScreens();
    }

    public void initializeScreens() {
        ScreenRegistry.<CharmsScreenHandler, CharmsInventoryScreen>register(CharmingMod.CHARMS_SCREEN_HANDLER_TYPE, CharmsInventoryScreen::new);
    }

}
