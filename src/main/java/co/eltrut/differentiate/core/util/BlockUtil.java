package co.eltrut.differentiate.core.util;

import net.minecraft.block.BlockState;
import net.minecraft.state.Property;

public class BlockUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static BlockState transferAllBlockStates(BlockState initial, BlockState end) {
		BlockState state = end;
		
		for (Property property : initial.getProperties()) {
			state = state.with(property, initial.get(property));
		}
		return state;
	}
	
}
