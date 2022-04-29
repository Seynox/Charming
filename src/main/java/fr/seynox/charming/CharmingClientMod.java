package fr.seynox.charming;

import fr.seynox.charming.client.KeyBinds;
import net.fabricmc.api.ClientModInitializer;

public class CharmingClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBinds.registerKeyBindings();
    }

}
