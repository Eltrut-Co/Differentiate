package co.eltrut.differentiate.core.registrator.sub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	
	public RegistryObject<Block> createSlabBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_slab", () -> new SlabBlock(properties), Groups.SLABS, mods);
	}
	
	public RegistryObject<Block> createStairsBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_stairs", () -> new DifferStairsBlock(() -> parent.getDefaultState(), properties), Groups.STAIRS, mods);
	}
	
	public RegistryObject<Block> createWallBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_wall", () -> new WallBlock(properties), Groups.WALLS, mods);
	}
	
	public List<RegistryObject<Block>> createSlabStairsWall(Block parent, Block.Properties properties, String ...mods) {
		return new ArrayList<RegistryObject<Block>>(Arrays.asList(this.createSlabBlock(parent, properties, mods), this.createStairsBlock(parent, properties, mods), this.createWallBlock(parent, properties, mods)));
	}
	
}
