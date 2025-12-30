package holiday;

import holiday.block.HolidayServerBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class HolidayServerBlockLootTableProvider extends FabricBlockLootTableProvider {
    public HolidayServerBlockLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(output, registries);
    }

    @Override
    public void generate() {
        this.addDrop(HolidayServerBlocks.REDSTONE_SAND);
        this.addDrop(HolidayServerBlocks.TINY_POTATO);
        this.addDrop(HolidayServerBlocks.GOLDEN_HOPPER);
        this.addDrop(HolidayServerBlocks.TELE_INHIBITOR);
        this.addDrop(HolidayServerBlocks.CHUNK_LOADER);

        this.lootTables.forEach((key, lootTable) -> {
            lootTable.randomSequenceId(key.getValue());
        });
    }
}
