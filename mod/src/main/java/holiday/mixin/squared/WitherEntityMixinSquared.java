package holiday.mixin.squared;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import holiday.idkwheretoputthis.WitherEntityExtension;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WitherEntity.class, priority = 1500)
public class WitherEntityMixinSquared extends HostileEntity {
    private WitherEntityMixinSquared(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /*
        ChainStyleWither compatability
     */

    @SuppressWarnings("all")
    @TargetHandler(
        mixin = "dev.louis.chainstylewither.mixin.WitherBossMixin",
        name = "customServerAiStep"
    )
    @WrapMethod(
        method = "@MixinSquared:Handler"
    )
    private void wrapCustomServerAiStep(ServerWorld world, CallbackInfo ci, Operation<Void> original) {
        if (!((WitherEntityExtension)(Object)this).fabric_holiday_25$isInOverWorld()) {
            original.call(world, ci);
        }
    }

    @SuppressWarnings("all")
    @TargetHandler(
        mixin = "dev.louis.chainstylewither.mixin.WitherBossMixin",
        name = "updatePostDeath"
    )
    @WrapMethod(
        method = "@MixinSquared:Handler"
    )
    private void wrapUpdatePostDeath(Operation<Void> original) {
        if (((WitherEntityExtension)(Object)this).fabric_holiday_25$isInOverWorld()) {
            super.updatePostDeath();
        } else {
            original.call();
        }
    }
}
