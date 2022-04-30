package fr.seynox.charming;

import fr.seynox.charming.inventory.CharmsInventory;
import fr.seynox.charming.inventory.gui.CharmsScreenHandler;
import fr.seynox.charming.network.PacketsConstants;
import fr.seynox.charming.services.CharmMenuService;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharmingMod implements ModInitializer {

	public static final String MOD_ID = "charming";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ScreenHandlerType<CharmsScreenHandler> CHARMS_SCREEN_HANDLER_TYPE;

	static {
		CHARMS_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(CharmsInventory.ID, CharmsScreenHandler::new);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Charming Mod");

		initializePacketListeners();
	}

	public void initializePacketListeners() {
		ServerPlayNetworking.registerGlobalReceiver(
				PacketsConstants.CHARMS_INVENTORY_PACKET_ID,
				(server, player, handler, buf, responseSender) -> CharmMenuService.onCharmsInventoryPacket(server, player));

	}
}
