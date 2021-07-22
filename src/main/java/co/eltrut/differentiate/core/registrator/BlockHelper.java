package co.eltrut.differentiate.core.registrator;

import java.util.function.Supplier;

import org.apache.commons.lang3.ArrayUtils;

import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.item.FuelBlockItem;
import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHelper extends AbstractHelper<Block> {
	
	protected final ItemHelper itemRegister;
	
	public BlockHelper(Registrator parent) {
		super(parent, ForgeRegistries.BLOCKS);
		itemRegister = this.parent.getHelper(ForgeRegistries.ITEMS);
	}
	
	public RegistryObject<Block> createBlock(String name, Supplier<Block> block, Item.Properties props) {
		RegistryObject<Block> registeredBlock = this.registry.register(name, block);
		this.itemRegister.createItem(name, () -> new BlockItem(registeredBlock.get(), props));
		
		return registeredBlock;
	}
	
	public RegistryObject<Block> createSimpleBlock(String name, Supplier<Block> block, ItemGroup group, String ...mods) {
		return this.createBlock(name, block, GroupUtil.getProps(group, mods));
	}
	
	public RegistryObject<Block> createFuelBlock(String name, Supplier<Block> block, Item.Properties props, int burnTime) {
		RegistryObject<Block> registeredBlock = this.registry.register(name, block);
		this.itemRegister.createItem(name, () -> new FuelBlockItem(registeredBlock.get(), props, burnTime));
		
		return registeredBlock;
	}
	
	public RegistryObject<Block> createSimpleFuelBlock(String name, Supplier<Block> block, ItemGroup group, int burnTime, String ...mods) {
		return this.createFuelBlock(name, block, GroupUtil.getProps(group, mods), burnTime);
	}
	
	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Supplier<Block> block, Properties props, ItemGroup group, String ...mods) {
		RegistryObject<Block> baseBlock = this.createSimpleBlock(name, block, group, mods);
		RegistryObject<Block> slabBlock = this.createSlabBlock(name, props, mods);
		RegistryObject<Block> stairsBlock = this.createStairsBlock(name, () -> new DifferStairsBlock(baseBlock.get()::defaultBlockState, props), mods);
		RegistryObject<Block> wallBlock = this.createWallBlock(name, props, mods);
		RegistryObject<Block> verticalSlabBlock = this.createVerticalSlabBlock(name, props, mods);
		return new VariantBlocksRepo.Builder().setBlock(baseBlock).setSlabBlock(slabBlock).setStairsBlock(stairsBlock).setWallBlock(wallBlock).setVerticalSlabBlock(verticalSlabBlock).build();
	}
	
	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Properties props, ItemGroup group, String ...mods) {
		return this.createSimpleBlockWithVariants(name, () -> new Block(props), props, group, mods);
	}
	
	protected RegistryObject<Block> createSlabBlock(String name, Properties props, String ...mods) {
		String prefix = name.endsWith("bricks") ? name.replace("_bricks", "_brick") : name;
		return this.createSimpleBlock(prefix + "_slab", () -> new SlabBlock(props), ItemGroup.TAB_BUILDING_BLOCKS, mods);
	}
	
	protected RegistryObject<Block> createStairsBlock(String name, Supplier<Block> block, String ...mods) {
		String prefix = name.endsWith("bricks") ? name.replace("_bricks", "_brick") : name;
		return this.createSimpleBlock(prefix + "_stairs", block, ItemGroup.TAB_BUILDING_BLOCKS, mods);
	}
	
	protected RegistryObject<Block> createWallBlock(String name, Properties props, String ...mods) {
		String prefix = name.endsWith("bricks") ? name.replace("_bricks", "_brick") : name;
		return this.createSimpleBlock(prefix + "_wall", () -> new WallBlock(props), ItemGroup.TAB_DECORATIONS, mods);
	}
	
	protected RegistryObject<Block> createVerticalSlabBlock(String name, Properties props, String ...mods) {
		String prefix = name.endsWith("bricks") ? name.replace("_bricks", "_brick") : name;
		String[] modsWithQuark = ArrayUtils.contains(mods, "quark") ? mods : ArrayUtils.add(mods, "quark");
		return this.createSimpleBlock(prefix + "_vertical_slab", () -> new VerticalSlabBlock(props), ItemGroup.TAB_BUILDING_BLOCKS, modsWithQuark);
	}
	
}
