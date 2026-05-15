package co.eltrut.differentiate.core.test;

import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.event.LoadEvent;
import co.eltrut.differentiate.core.registrator.BlockHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredBlock;

@EventBusSubscriber(modid = Differentiate.MOD_ID)
public class TestBlocks {
    @SubscribeEvent
    public static void load(LoadEvent event) {}

    public static final BlockHelper BLOCK_HELPER = Differentiate.REGISTRATOR.getHelper(Registries.BLOCK);

    public static final DeferredBlock<Block> DIRT_SLAB = BLOCK_HELPER.createBlock("dirt_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)),
            CreativeModeTabs.BUILDING_BLOCKS);

}
