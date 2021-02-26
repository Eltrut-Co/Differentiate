package co.eltrut.differentiate.core.registrator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.interf.IFuelItem;
import co.eltrut.differentiate.common.item.FuelBlockItem;
import co.eltrut.differentiate.core.util.GroupUtil;
import co.eltrut.differentiate.core.util.GroupUtil.Groups;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHelper extends AbstractHelper<Block> {
	
	public static final int SLAB = 0;
	public static final int STAIRS = 1;
	public static final int WALL = 2;
	public static final int VERTICAL_SLAB = 3;
	
	public BlockHelper(Registrator parent) {
		super(parent, ForgeRegistries.BLOCKS);
	}
	
	public RegistryObject<Block> createBlock(String name, Supplier<Block> block, Item.Properties props) {
		RegistryObject<Block> registeredBlock = this.registry.register(name, block);
		ItemHelper itemRegister = this.parent.getItemSubRegistrator();
		int burnTime = block.get() instanceof IFuelItem ? ((IFuelItem)block.get()).getBurnTime() : 0;
		itemRegister.createItem(name, () -> new FuelBlockItem(registeredBlock.get(), props, burnTime));
		
		return registeredBlock;
	}
	
	public RegistryObject<Block> createSimpleBlock(String name, Supplier<Block> block, ItemGroup group, String ...mods) {
		return this.createBlock(name, block, GroupUtil.getProps(group, mods));
	}

	public RegistryObject<Block> createSlabBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_slab", () -> new SlabBlock(properties), Groups.slabs(mods));
	}
	
	public RegistryObject<Block> createStairsBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_stairs", () -> new DifferStairsBlock(() -> parent.getDefaultState(), properties), Groups.stairs(mods));
	}
	
	public RegistryObject<Block> createWallBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_wall", () -> new WallBlock(properties), Groups.walls(mods));
	}
	
	public RegistryObject<Block> createVerticalSlabBlock(Block parent, Block.Properties properties, String ...mods) {
		return this.createBlock(parent.getRegistryName().getPath() + "_vertical_slab", () -> new VerticalSlabBlock(properties), Groups.vertSlabs(mods));
	}
	
	public List<RegistryObject<Block>> createAllVariants(Block parent, Block.Properties properties, String ...mods) {
		return new ArrayList<RegistryObject<Block>>(Arrays.asList(this.createSlabBlock(parent, properties, mods),
				this.createStairsBlock(parent, properties, mods),
				this.createWallBlock(parent, properties, mods),
				this.createVerticalSlabBlock(parent, properties, mods)));
	}
	
}
