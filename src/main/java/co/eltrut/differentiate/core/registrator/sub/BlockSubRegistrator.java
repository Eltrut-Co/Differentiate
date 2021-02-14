package co.eltrut.differentiate.core.registrator.sub;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.core.helper.BlockHelper;
import co.eltrut.differentiate.core.registrator.Registrator;
import co.eltrut.differentiate.core.util.GroupUtil.Groups;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockSubRegistrator extends AbstractSubRegistrator<Block> {
	public final Map<Block, BlockHelper> block_map = new HashMap<>();
	
	public BlockSubRegistrator(Registrator parent) {
		super(parent, ForgeRegistries.BLOCKS);
	}

	public BlockHelper create() {
		return new BlockHelper(this);
	}
	
	public RegistryObject<Block> createBlock(String name, Supplier<Block> block, ItemGroup group, String ...mods) {
		return new BlockHelper(this).setName(name).setBlock(block).setItem().setItemGroup(group).setMods(mods).done().build();
	}
	
	public RegistryObject<Block> createFuelBlock(String name, Supplier<Block> block, ItemGroup group, int burnTime, String ...mods) {
		return new BlockHelper(this).setName(name).setBlock(block).setItem().setItemGroup(group).setBurnTime(burnTime).setMods(mods).done().build();
	}
	
	public void createSlab(Block parent) {
		this.createBlock(parent.getRegistryName() + "_slab", () -> new SlabBlock(Block.Properties.from(parent)), Groups.SLABS, this.block_map.get(parent).getChild().getMods());
	}
	
	public void createStairs(Block parent) {
		this.createBlock(parent.getRegistryName() + "_stairs", () -> new DifferStairsBlock(() -> parent.getDefaultState(), Block.Properties.from(parent)), Groups.STAIRS, this.block_map.get(parent).getChild().getMods());
	}
	
	public void createWall(Block parent) {
		this.createBlock(parent.getRegistryName() + "_wall", () -> new WallBlock(Block.Properties.from(parent)), Groups.WALLS, this.block_map.get(parent).getChild().getMods());
	}
	
	public Map<Block, BlockHelper> getBlockMap() {
		return this.block_map;
	}
	
}
