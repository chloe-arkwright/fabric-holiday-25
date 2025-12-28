package holiday.mixin;

import holiday.tag.HolidayServerItemTags;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SingleStackStorage.class)
public class SingleStackStorageMixin {
    @Inject(method="canExtract", at = @At("HEAD"), cancellable = true)
    private void mite(ItemVariant itemVariant, CallbackInfoReturnable<Boolean> cir) {
        if (itemVariant.getRegistryEntry().isIn(HolidayServerItemTags.HOPPER_TRAPPED)) cir.setReturnValue(false);
    }
}
