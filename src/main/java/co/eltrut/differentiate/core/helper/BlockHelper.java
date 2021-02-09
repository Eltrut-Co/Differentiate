package co.eltrut.differentiate.core.helper;

import java.util.function.Supplier;

import co.eltrut.differentiate.core.util.CompatUtil;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHelper extends AbstractHelper<Block> {
	
	private String name;
	private Supplier<Block> block;
	private final ItemHelper HELPER;
	
	public BlockHelper(String modid, ItemHelper helper) {
		super(modid, ForgeRegistries.BLOCKS);
		this.HELPER = helper;
	}
	
	public BlockHelper setName(String name) {
		this.name = name;
		return this;
	}
	
	public BlockHelper setBlock(Supplier<Block> block) {
		this.block = block;
		return this;
	}
	
	public BlockHelper setItemGroup(ItemGroup group, String ...mods) {
		ItemGroup determinedGroup = CompatUtil.areModsLoaded(mods) ? group : null;
		this.HELPER.getDeferredRegister().register(this.name, () -> new BlockItem(this.block.get(), new Properties().group(determinedGroup)));
		return this;
	}
	
	public RegistryObject<Block> register() {
		return this.REGISTRY.register(this.name, this.block);
	}
	
}
