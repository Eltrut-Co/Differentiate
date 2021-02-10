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
	
	private String name;
	private Supplier<Block> block;
	private ItemGroup group;
	private String[] mods;
	
	public BlockHelper(Registrator parent) {
		super(parent, ForgeRegistries.BLOCKS);
	}
	
	public BlockHelper setName(String name) {
		this.name = name;
		return this;
	}
	
	public BlockHelper setBlock(Supplier<Block> block) {
		this.block = block;
		return this;
	}
	
	public BlockHelper setItemGroup(ItemGroup group) {
		this.group = group;
		return this;
	}
	
	public BlockHelper setMods(String ...mods) {
		this.mods = mods;
		return this;
	}
	
	public RegistryObject<Block> build() {
		RegistryObject<Block> registeredBlock = this.REGISTRY.register(this.name, this.block);
		ItemGroup determinedGroup = CompatUtil.areModsLoaded(this.mods) ? this.group : null;
		this.PARENT.ITEM_HELPER.registerItem(this.name, () -> new BlockItem(registeredBlock.get(), new Item.Properties().group(determinedGroup)));
		return registeredBlock;
	}
	
	public RegistryObject<Block> registerBlock(String name, Supplier<Block> block, ItemGroup group, String ...mods) {
		return this.setName(name).setBlock(block).setItemGroup(group).setMods(mods).build();
	}
	
}
