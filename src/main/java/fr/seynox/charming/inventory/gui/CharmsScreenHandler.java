package fr.seynox.charming.inventory.gui;

import fr.seynox.charming.CharmingMod;
import fr.seynox.charming.inventory.ICharmStorage;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;

public class CharmsScreenHandler extends SyncedGuiDescription {

    public CharmsScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(CharmingMod.CHARMS_SCREEN_HANDLER_TYPE, syncId, playerInventory, ((ICharmStorage)playerInventory.player).getCharmsInventory(), null);

        int slotsSize = blockInventory.size();

        WGridPanel root = new WGridPanel((slotsSize*3) + 2);
        setRootPanel(root);
        root.setInsets(Insets.ROOT_PANEL);

        for(int i=0; i < slotsSize; i++) {
            WItemSlot itemSlot = WItemSlot.of(blockInventory, i);
            root.add(itemSlot, i+1, 1);
        }

        root.add(this.createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }

}
