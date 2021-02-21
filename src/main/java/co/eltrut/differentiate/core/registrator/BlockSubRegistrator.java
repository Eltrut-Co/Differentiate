package co.eltrut.differentiate.core.registrator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.common.interf.IFuelItem;
import co.eltrut.differentiate.common.item.FuelBlockItem;
import co.eltrut.differentiate.core.util.GroupUtil.SpecialGroups;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockSubRegistrator extends AbstractSubRegistrator<Block> {
	
	public BlockSubRegistrator(Registrator parent) {
		super(parent, ForgeRegistries.BLOCKS);
	}
	
	public RegistryObject<Block> createBlock(String name, Supplier<Block> block, Item.Properties props, String ...mods) {
		RegistryObject<Block> registeredBlock = this.registry.register(name, block);
		
		ItemSubRegistrator itemRegister = this.parent.getItemSubRegistrator();
		int burnTime = block.get() instanceof IFuelItem ? ((IFuelItem)block).getBurnTime() : 0;
		itemRegister.createItem(name, () -> new FuelBlockItem(registeredBlock.get(), props, burnTime));
		
		return registeredBlock;
	}

	public RegistryObject<Block> createSlabBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_slab", () -> new SlabBlock(properties), SpecialGroups.SLABS, mods);
	}
	
	public RegistryObject<Block> createStairsBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_stairs", () -> new DifferStairsBlock(() -> parent.getDefaultState(), properties), SpecialGroups.STAIRS, mods);
	}
	
	public RegistryObject<Block> createWallBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_wall", () -> new WallBlock(properties), SpecialGroups.WALLS, mods);
	}
	
	public List<RegistryObject<Block>> createSlabStairsWall(Block parent, Block.Properties properties, String ...mods) {
		return new ArrayList<RegistryObject<Block>>(Arrays.asList(this.createSlabBlock(parent, properties, mods), this.createStairsBlock(parent, properties, mods), this.createWallBlock(parent, properties, mods)));
	}
	
}