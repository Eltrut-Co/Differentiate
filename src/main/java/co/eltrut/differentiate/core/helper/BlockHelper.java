package co.eltrut.differentiate.core.helper;

import java.util.function.Supplier;

import co.eltrut.differentiate.core.util.CompatUtil;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHelper extends AbstractHelper<Block> {
	
	public BlockHelper(Registrator parent) {
		super(parent, ForgeRegistries.BLOCKS);
	}
	
	public RegistryObject<Block> block(String name, Supplier<Block> block, ItemGroup group, String ...mods) {
		RegistryObject<Block> registeredBlock = this.REGISTRY.register(name, block);
		ItemGroup determinedGroup = CompatUtil.areModsLoaded(mods) ? group : null;
		this.PARENT.ITEM_HELPER.item(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties().group(determinedGroup)));
		return registeredBlock;
	}
	
}
