package co.eltrut.differentiate.core.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import co.eltrut.differentiate.common.blocks.base.IDifferBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHelper extends AbstractHelper<Block> {
	
	private Map<RegistryObject<Block>, String[]> map = new HashMap<>();
	
	public BlockHelper(Registrator parent) {
		super(parent, ForgeRegistries.BLOCKS);
	}
	
	public RegistryObject<Block> block(String name, Supplier<Block> block, String ...mods) {
		RegistryObject<Block> registeredBlock = this.REGISTRY.register(name, block);
		map.put(registeredBlock, mods);
		return registeredBlock;
	}
	
	public void registerBlockItems(final RegistryEvent.Register<Item> event) {
		for (RegistryObject<Block> blockObject : this.REGISTRY.getEntries()) {
			Block block = blockObject.get();
			if (blockObject.get() instanceof IDifferBlock) {
				ItemGroup group = ((IDifferBlock) block).group();
				BlockItem blockItem = (BlockItem) new BlockItem(block, new Item.Properties().group(group)).setRegistryName(block.getRegistryName());
				event.getRegistry().register(blockItem);
			}
		}
	}
	
}
