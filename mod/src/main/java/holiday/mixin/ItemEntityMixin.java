package holiday.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Debug(export = true)
@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Definition(id = "stack1", local = @Local(type = ItemStack.class, ordinal = 0, argsOnly = true))
    @Definition(id = "stack2", local = @Local(type = ItemStack.class, ordinal = 1, argsOnly = true))
    @Definition(id = "getCount", method = "Lnet/minecraft/item/ItemStack;getCount()I")
    @Definition(id = "getMaxCount", method = "Lnet/minecraft/item/ItemStack;getMaxCount()I")
    @Expression("@(stack2.getCount() + stack1.getCount() > stack2.getMaxCount())")
    @WrapOperation(method = "canMerge(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean canmergeignorestacksize(int left, int right, Operation<Boolean> original) {
        return false;
    }

    @Definition(id = "getCount", method = "Lnet/minecraft/item/ItemStack;getCount()I")
    @Definition(id = "getMaxCount", method = "Lnet/minecraft/item/ItemStack;getMaxCount()I")
    @Definition(id = "itemStack", local = @Local(type = ItemStack.class))
    @Expression("@(itemStack.getCount() < itemStack.getMaxCount())")
    @WrapOperation(method = "canMerge()Z", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean canmergeignorestacksize2(int left, int right, Operation<Boolean> original) {
        return true;
    }

    @Redirect(method = "merge(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/item/ItemStack;", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 1))
    private static int trymergeignorestacksize(int i, int j, @Local(argsOnly = true, ordinal = 0) ItemStack stack1, @Local(argsOnly = true, ordinal = 1) ItemStack stack2) {
        return stack2.getCount();
    }
}
