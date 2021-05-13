package co.eltrut.differentiate.core.registrator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
	
	public <T> List<RegistryObject<Block>> createMultipleBlocks(T[] array, Function<? super T, ? extends RegistryObject<Block>> mapper) {
		return Arrays.stream(array).map(mapper).collect(Collectors.toList());
	}
	
	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Supplier<Block> block, Properties props, ItemGroup group, String ...mods) {
		String[] modsWithQuark = ArrayUtils.contains(mods, "quark") ? mods : ArrayUtils.add(mods, "quark");
		RegistryObject<Block> baseBlock = this.createSimpleBlock(name, block, group, mods);
		RegistryObject<Block> slabBlock = this.createSimpleBlock(name + "_slab", () -> new SlabBlock(props), ItemGroup.TAB_BUILDING_BLOCKS, mods);
		RegistryObject<Block> stairsBlock = this.createSimpleBlock(name + "_stairs", () -> new DifferStairsBlock(baseBlock.get()::defaultBlockState, props), ItemGroup.TAB_BUILDING_BLOCKS, mods);
		RegistryObject<Block> wallBlock = this.createSimpleBlock(name + "_wall", () -> new WallBlock(props), ItemGroup.TAB_DECORATIONS, mods);
		RegistryObject<Block> verticalSlabBlock = this.createSimpleBlock(name + "_vertical_slab", () -> new VerticalSlabBlock(props), ItemGroup.TAB_BUILDING_BLOCKS, modsWithQuark);
		return new VariantBlocksRepo.Builder().setBlock(baseBlock).setSlabBlock(slabBlock).setStairsBlock(stairsBlock).setWallBlock(wallBlock).setVerticalSlabBlock(verticalSlabBlock).build();
	}
	
	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Properties props, ItemGroup group, String ...mods) {
		return this.createSimpleBlockWithVariants(name, () -> new Block(props), props, group, mods);
	}
	
}
