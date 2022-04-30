package fr.seynox.charming.mixin;

import com.mojang.authlib.GameProfile;
import fr.seynox.charming.inventory.ICharmStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    protected ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "copyFrom", at = @At("RETURN"))
    public void copyFrom(ServerPlayerEntity oldPlayer, boolean alive) {

        if(alive || this.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY) || oldPlayer.isSpectator()) {
            ((ICharmStorage)this).setCharmsInventory(((ICharmStorage)oldPlayer).getCharmsInventory());
        }
    }

}
