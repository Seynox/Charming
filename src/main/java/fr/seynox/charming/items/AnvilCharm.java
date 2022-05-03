package fr.seynox.charming.items;

import fr.seynox.charming.inventory.CharmsInventory;
import fr.seynox.charming.inventory.ICharmStorage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.Set;

import static fr.seynox.charming.CharmingMod.MOD_ID;

public class AnvilCharm extends Charm {

    public static final Identifier ID = new Identifier(MOD_ID, "anvil_charm");

    public static double onKnockback(LivingEntity entity, double knockbackStrength) {
        if(entity instanceof ICharmStorage charmStorage) {
            CharmsInventory charms = charmStorage.getCharmsInventory();
            boolean hasAnvilCharm = charms.containsAny(Set.of(CharmingItems.ANVIL_CHARM));

            if(hasAnvilCharm) {
                return 0d;
            }
        }

        return knockbackStrength;
    }

}
