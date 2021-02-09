package co.eltrut.differentiate.core.helper;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.blocks.base.IItemGroupBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHelper extends AbstractHelper<Block> {
	
	public BlockHelper(Registrator parent) {
		super(parent, ForgeRegistries.BLOCKS);
	}
	
	public RegistryObject<Block> block(String name, Supplier<Block> block) {
		return this.REGISTRY.register(name, block);
	}
	
	@Override
	public void register(IEventBus bus) {
		for (RegistryObject<Block> blockObject : this.REGISTRY.getEntries()) {
			Block block = blockObject.get();
			if (block instanceof IItemGroupBlock) {
				this.PARENT.ITEM_HELPER.item(block.getRegistryName().getPath(), () -> new BlockItem(block, new Properties().group(((IItemGroupBlock) block).group())));
			}
		}
		super.register(bus);
	}
	
}
