package holiday.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneBlockMixin implements Fertilizable {

    @Shadow
    private static boolean canGrow(BlockState state, ServerWorld world, BlockPos pos) {
        throw new AssertionError("Mixin failed to apply");
    }

    @Shadow
    private static void tryGrow(ServerWorld world, BlockPos pos, Direction direction) {
        throw new AssertionError("Mixin failed to apply");
    }

    @Shadow
    @Final
    public static EnumProperty<Direction> VERTICAL_DIRECTION;

    @Shadow
    @Nullable
    private static BlockPos getTipPos(BlockState state, WorldAccess world, BlockPos pos, int range, boolean allowMerged) {
        throw  new AssertionError("Mixin failed to apply");
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        if (!(world instanceof ServerWorld serverWorld)) return false;

        BlockPos tip = getTipPos(state, serverWorld, pos, 11, false);
        return tip != null && canGrow(
            world.getBlockState(tip),
            serverWorld,
            tip
        );
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        if (!(world instanceof ServerWorld serverWorld)) return false;

        BlockPos tip = getTipPos(state, serverWorld, pos, 11, false);
        return tip != null && canGrow(
            world.getBlockState(tip),
            serverWorld,
            tip
        );
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos tip = getTipPos(state, world, pos, 11, false);
        if (tip == null) return;

        BlockState tipState = world.getBlockState(tip);
        tryGrow(world, tip, tipState.get(VERTICAL_DIRECTION));
    }
}
