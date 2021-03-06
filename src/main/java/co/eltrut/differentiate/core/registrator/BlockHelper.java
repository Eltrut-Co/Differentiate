package co.eltrut.differentiate.core.registrator;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.item.FuelBlockItem;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.Block;
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
	
}
