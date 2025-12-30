package holiday.block;

import com.mojang.serialization.MapCodec;
import holiday.state.HolidayServerProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class ChunkLoaderBlock extends Block {
    public static final MapCodec<ChunkLoaderBlock> CODEC = createCodec(ChunkLoaderBlock::new);
    public static final BooleanProperty ACTIVATED = HolidayServerProperties.ACTIVATED;

    public ChunkLoaderBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(ACTIVATED, false));
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        boolean newActivatedState = !state.get(ACTIVATED);
        world.setBlockState(pos, state.with(ACTIVATED, newActivatedState));
        world.playSound(player, pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (world instanceof ServerWorld serverWorld) {
            boolean activated = state.get(ACTIVATED);

            ChunkPos chunkPos = new ChunkPos(pos);
            serverWorld.setChunkForced(chunkPos.x, chunkPos.z, newActivatedState);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }
}
