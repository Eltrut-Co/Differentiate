package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.core.util.BlockUtil;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LogStairBlock extends StairBlock {

    private final Supplier<Block> strippedBlock;

    public LogStairBlock(Supplier<Block> strippedBlock, BlockState baseState, Properties properties) {
        super(baseState, properties);
        this.strippedBlock = strippedBlock;
    }

    @Override
    public BlockState getToolModifiedState(@NotNull BlockState state, @NotNull UseOnContext context,
                                           @NotNull ItemAbility itemAbility, boolean simulate) {
        if (itemAbility == ItemAbilities.AXE_STRIP) {
            if (this.strippedBlock != null) {
                return BlockUtil.transferAllBlockStates(state, this.strippedBlock.get().defaultBlockState());
            }
            return null;
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }

}
