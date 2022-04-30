package fr.seynox.charming.inventory.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class CharmsInventoryScreen extends CottonInventoryScreen<CharmsScreenHandler> {

    public CharmsInventoryScreen(CharmsScreenHandler gui, PlayerInventory inventory, Text title) {
        this(gui, inventory.player, title);
    }

    public CharmsInventoryScreen(CharmsScreenHandler gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }

}
