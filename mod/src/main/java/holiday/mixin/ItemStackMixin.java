package holiday.mixin;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @ModifyConstant(method = "method_57371", constant = @Constant(intValue = 99))
    private static int removeLimit(int constant) {
        return Integer.MAX_VALUE;
    }
}
