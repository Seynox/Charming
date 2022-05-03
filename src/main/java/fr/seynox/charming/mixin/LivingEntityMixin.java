package fr.seynox.charming.mixin;

import fr.seynox.charming.items.AnvilCharm;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    protected LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyArgs(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;takeKnockback(DDD)V"))
    public void onDamageKnockback(Args args, DamageSource source, float amount) {
        double newKnockbackStrength = AnvilCharm.onKnockback((LivingEntity) (Object) this, args.get(0));
        args.set(0, newKnockbackStrength);
    }

}
