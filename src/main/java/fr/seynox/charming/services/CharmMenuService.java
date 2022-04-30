package fr.seynox.charming.services;

import fr.seynox.charming.inventory.gui.CharmsScreenHandler;
import fr.seynox.charming.network.PacketsConstants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import static fr.seynox.charming.CharmingMod.LOGGER;
import static fr.seynox.charming.CharmingMod.MOD_ID;

public class CharmMenuService {

    private CharmMenuService() {}

    public static void toggleCharmMenu() {
        LOGGER.debug("Toggling charm menu");
        ClientPlayNetworking.send(PacketsConstants.CHARMS_INVENTORY_PACKET_ID, PacketByteBufs.empty());
    }

    public static void onCharmsInventoryPacket(MinecraftServer server, ServerPlayerEntity serverPlayer) {
        server.execute(() -> {
            Text title = new TranslatableText("name.%s.inventory.title".formatted(MOD_ID));

            SimpleNamedScreenHandlerFactory screen = new SimpleNamedScreenHandlerFactory(
                    (syncId, inv, player) -> new CharmsScreenHandler(syncId, inv),
                    title);

            serverPlayer.openHandledScreen(screen);
        });
    }
}
