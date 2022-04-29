package fr.seynox.charming;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharmingMod implements ModInitializer {

	public static final String MOD_ID = "charming";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Charming Mod");
	}
}
