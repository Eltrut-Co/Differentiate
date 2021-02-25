package co.eltrut.differentiate.core.registrator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.lang3.ArrayUtils;

import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.interf.IFuelItem;
import co.eltrut.differentiate.common.item.FuelBlockItem;
import co.eltrut.differentiate.core.util.CompatUtil;
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
		
		Item.Properties determinedProps = mods.length > 0 && CompatUtil.areModsLoaded(mods) ? props : props.group(null);
		
		ItemSubRegistrator itemRegister = this.parent.getItemSubRegistrator();
		int burnTime = block.get() instanceof IFuelItem ? ((IFuelItem)block.get()).getBurnTime() : 0;
		itemRegister.createItem(name, () -> new FuelBlockItem(registeredBlock.get(), determinedProps, burnTime));
		
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
	
	public RegistryObject<Block> createVerticalSlabBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_vertical_slab", () -> new VerticalSlabBlock(properties), SpecialGroups.VERTICAL_SLABS, ArrayUtils.add(mods, "quark"));
	}
	
	public List<RegistryObject<Block>> createSlabStairsWall(Block parent, Block.Properties properties, String ...mods) {
		return new ArrayList<RegistryObject<Block>>(Arrays.asList(this.createSlabBlock(parent, properties, mods), this.createStairsBlock(parent, properties, mods), this.createWallBlock(parent, properties, mods)));
	}
	
}
