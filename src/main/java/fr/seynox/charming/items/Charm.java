package fr.seynox.charming.items;

import fr.seynox.charming.CharmingMod;
import fr.seynox.charming.inventory.CharmsInventory;
import fr.seynox.charming.inventory.ICharmStorage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Charm extends Item {

    public Charm(Settings settings) {
        super(settings);
    }

    public Charm() {
        this(new FabricItemSettings()
                .maxCount(1)
                .group(CharmingMod.CHARMS_TAB));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        CharmsInventory charms = ((ICharmStorage)player).getCharmsInventory();
        ItemStack stack = player.getStackInHand(hand);

        boolean canInsert = charms.canInsert(stack);
        if(!canInsert) {
            return TypedActionResult.pass(stack);
        }

        if(!world.isClient) {
            charms.addStack(stack.copy());
            stack.setCount(0);
        }

        return TypedActionResult.consume(stack);
    }
}
