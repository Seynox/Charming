package fr.seynox.charming;

import fr.seynox.charming.inventory.CharmsInventory;
import fr.seynox.charming.inventory.gui.CharmsScreenHandler;
import fr.seynox.charming.items.AnvilCharm;
import fr.seynox.charming.items.CharmingItems;
import fr.seynox.charming.network.PacketsConstants;
import fr.seynox.charming.network.CharmsInventoryPacketHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharmingMod implements ModInitializer {

	public static final String MOD_ID = "charming";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ItemGroup CHARMS_TAB = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, MOD_ID),
			() -> new ItemStack(Items.AIR)
	);

	public static final ScreenHandlerType<CharmsScreenHandler> CHARMS_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(CharmsInventory.ID, CharmsScreenHandler::new);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Charming Mod");

		initializePacketListeners();
		initializeItems();
	}

	public void initializePacketListeners() {
		ServerPlayNetworking.registerGlobalReceiver(
				PacketsConstants.CHARMS_INVENTORY_PACKET_ID,
				(server, player, handler, buf, responseSender) -> CharmsInventoryPacketHandler.onCharmsInventoryPacket(server, player));
	}

	public void initializeItems() {
		Registry.register(Registry.ITEM, AnvilCharm.ID, CharmingItems.ANVIL_CHARM);
	}
}
