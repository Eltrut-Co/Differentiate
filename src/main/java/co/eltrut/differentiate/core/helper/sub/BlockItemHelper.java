package co.eltrut.differentiate.core.helper.sub;

import co.eltrut.differentiate.core.helper.BlockHelper;
import co.eltrut.differentiate.core.util.CompatUtil;
import net.minecraft.item.ItemGroup;

public class BlockItemHelper extends AbstractSubHelper<BlockHelper> {

	private ItemGroup group;
	private String[] mods;
	private int burnTime = 0;
	
	public BlockItemHelper(BlockHelper parent) {
		super(parent);
	}
	
	public BlockItemHelper setItemGroup(ItemGroup group) {
		this.group = group;
		return this;
	}
	
	public BlockItemHelper setMods(String ...mods) {
		this.mods = mods;
		return this;
	}
	
	public BlockItemHelper setBurnTime(int burnTime) {
		this.burnTime = burnTime;
		return this;
	}
	
	public BlockHelper done() {
		return this.parent;
	}
	
	public ItemGroup getDeterminedItemGroup() {
		return CompatUtil.areModsLoaded(this.mods) ? this.group : null;
	}
	
	public int getBurnTime() {
		return this.burnTime;
	}

}
