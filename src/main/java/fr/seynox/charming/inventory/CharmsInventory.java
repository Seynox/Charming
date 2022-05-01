package fr.seynox.charming.inventory;

import fr.seynox.charming.items.Charm;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

import static fr.seynox.charming.CharmingMod.MOD_ID;

public class CharmsInventory extends SimpleInventory {

    public static final Identifier ID = new Identifier(MOD_ID, "charm_inventory");
    public static final String NBT_KEY = "CharmingCharms";

    public static final int INVENTORY_SIZE = 6;

    public CharmsInventory() {
        super(INVENTORY_SIZE);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        boolean isCharm = stack.getItem() instanceof Charm;
        return isCharm && super.isValid(slot, stack);
    }

    @Override
    public void readNbtList(NbtList nbtList) {
        int i;
        for (i = 0; i < this.size(); ++i) {
            this.setStack(i, ItemStack.EMPTY);
        }
        for (i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            int j = nbtCompound.getByte("Slot") & 0xFF;
            if (j < 0 || j >= this.size()) continue;
            this.setStack(j, ItemStack.fromNbt(nbtCompound));
        }
    }

    @Override
    public NbtList toNbtList() {
        NbtList nbtList = new NbtList();
        for (int i = 0; i < this.size(); ++i) {
            ItemStack itemStack = this.getStack(i);
            if (itemStack.isEmpty()) continue;
            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.putByte("Slot", (byte)i);
            itemStack.writeNbt(nbtCompound);
            nbtList.add(nbtCompound);
        }
        return nbtList;
    }

}
