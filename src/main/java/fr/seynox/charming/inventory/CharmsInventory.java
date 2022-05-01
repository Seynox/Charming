package fr.seynox.charming.inventory;

import fr.seynox.charming.items.Charm;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

import java.util.Set;

import static fr.seynox.charming.CharmingMod.MOD_ID;

public class CharmsInventory extends SimpleInventory {

    public static final Identifier ID = new Identifier(MOD_ID, "charm_inventory");
    public static final String NBT_KEY = "CharmingCharms";

    public static final int INVENTORY_SIZE = 6;

    public CharmsInventory() {
        super(INVENTORY_SIZE);
    }

    public void dropAll(LivingEntity entity) {
        for(int i = 0; i < this.size(); ++i) {
            ItemStack stack = this.getStack(i);

            if(stack.isEmpty()) {
                continue;
            }

            if(entity instanceof PlayerEntity player) {
                player.dropItem(stack, true, false);
            } else {
                entity.dropStack(stack);
            }

            this.removeStack(i);
        }
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        boolean isCharm = stack.getItem() instanceof Charm;
        boolean alreadyEquipped = containsAny(Set.of(stack.getItem()));

        return isCharm && !alreadyEquipped && super.canInsert(stack);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        boolean isCharm = stack.getItem() instanceof Charm;
        boolean alreadyEquipped = containsAny(Set.of(stack.getItem()));

        return isCharm && !alreadyEquipped && super.isValid(slot, stack);
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
