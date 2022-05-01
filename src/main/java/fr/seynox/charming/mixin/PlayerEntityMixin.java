package fr.seynox.charming.mixin;

import fr.seynox.charming.inventory.CharmsInventory;
import fr.seynox.charming.inventory.ICharmStorage;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static fr.seynox.charming.inventory.CharmsInventory.NBT_KEY;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ICharmStorage {

    private CharmsInventory charmsInventory = new CharmsInventory();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    @Override
    public CharmsInventory getCharmsInventory() {
        return this.charmsInventory;
    }

    @Override
    public void setCharmsInventory(CharmsInventory charmsInventory) {
        this.charmsInventory = charmsInventory;
    }

    @Inject(method = "dropInventory", at = @At("RETURN"))
    public void dropInventory(CallbackInfo info) {
        if(!this.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
            this.charmsInventory.dropAll(this);
        }
    }

    // NBT Serialization/Deserialization

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        nbt.put(NBT_KEY, this.charmsInventory.toNbtList());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains(NBT_KEY, 9)) {
            this.charmsInventory.readNbtList(nbt.getList(NBT_KEY, 10));
        }
    }
}
