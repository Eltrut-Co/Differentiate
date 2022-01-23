package co.eltrut.differentiate.core.registrator;

import java.util.function.Supplier;

import org.apache.commons.lang3.ArrayUtils;

import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.registries.RegistryObject;
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
	
	public RegistryObject<Block> createSimpleBlock(String name, Supplier<Block> block, CreativeModeTab group, String ...mods) {
		return this.createBlock(name, block, GroupUtil.getProps(group, mods));
	}
	
//	public RegistryObject<Block> createFuelBlock(String name, Supplier<Block> block, Item.Properties props, int burnTime) {
//		RegistryObject<Block> registeredBlock = this.registry.register(name, block);
//		RegistryObject<Item> registeredItem = this.itemRegister.createItem(name, () -> new BlockItem(registeredBlock.get(), props));
//		ItemHelper.FUEL.put(registeredItem, burnTime);
//		return registeredBlock;
//	}
//	
//	public RegistryObject<Block> createSimpleFuelBlock(String name, Supplier<Block> block, CreativeModeTab group, int burnTime, String ...mods) {
//		return this.createFuelBlock(name, block, GroupUtil.getProps(group, mods), burnTime);
//	}
	
	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Supplier<Block> block, Properties props, CreativeModeTab group, String ...mods) {
		RegistryObject<Block> baseBlock = this.createSimpleBlock(name, block, group, mods);
		RegistryObject<Block> slabBlock = this.createSlabBlock(name, props, mods);
		RegistryObject<Block> stairsBlock = this.createStairsBlock(name, () -> new StairBlock(baseBlock.get()::defaultBlockState, props), mods);
		RegistryObject<Block> wallBlock = this.createWallBlock(name, props, mods);
		RegistryObject<Block> verticalSlabBlock = this.createVerticalSlabBlock(name, props, mods);
		return new VariantBlocksRepo.Builder().setBlock(baseBlock).setSlabBlock(slabBlock).setStairsBlock(stairsBlock).setWallBlock(wallBlock).setVerticalSlabBlock(verticalSlabBlock).build();
	}
	
	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Properties props, CreativeModeTab group, String ...mods) {
		return this.createSimpleBlockWithVariants(name, () -> new Block(props), props, group, mods);
	}
	
	protected RegistryObject<Block> createSlabBlock(String name, Properties props, String ...mods) {
		String prefix = name.endsWith("bricks") || name.endsWith("tiles") ? name.replace("_bricks", "_brick").replace("_tiles", "_tile") : name;
		return this.createSimpleBlock(prefix + "_slab", () -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
	}
	
	protected RegistryObject<Block> createStairsBlock(String name, Supplier<Block> block, String ...mods) {
		String prefix = name.endsWith("bricks") || name.endsWith("tiles") ? name.replace("_bricks", "_brick").replace("_tiles", "_tile") : name;
		return this.createSimpleBlock(prefix + "_stairs", block, CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
	}
	
	protected RegistryObject<Block> createWallBlock(String name, Properties props, String ...mods) {
		String prefix = name.endsWith("bricks") || name.endsWith("tiles") ? name.replace("_bricks", "_brick").replace("_tiles", "_tile") : name;
		return this.createSimpleBlock(prefix + "_wall", () -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, mods);
	}
	
	protected RegistryObject<Block> createVerticalSlabBlock(String name, Properties props, String ...mods) {
		String prefix = name.endsWith("bricks") || name.endsWith("tiles") ? name.replace("_bricks", "_brick").replace("_tiles", "_tile") : name;
		String[] modsWithQuark = ArrayUtils.contains(mods, "quark") ? mods : ArrayUtils.add(mods, "quark");
		return this.createSimpleBlock(prefix + "_vertical_slab", () -> new VerticalSlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modsWithQuark);
	}
	
}
