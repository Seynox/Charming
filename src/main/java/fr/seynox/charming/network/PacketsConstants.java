package fr.seynox.charming.network;

import net.minecraft.util.Identifier;

import static fr.seynox.charming.CharmingMod.MOD_ID;

public class PacketsConstants {

    private PacketsConstants() {}

    public static final Identifier CHARMS_INVENTORY_PACKET_ID = new Identifier(MOD_ID, "charm_inventory");

}
