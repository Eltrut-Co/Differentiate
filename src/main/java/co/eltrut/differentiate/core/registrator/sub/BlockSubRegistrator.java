package co.eltrut.differentiate.core.registrator.sub;

import java.util.function.Supplier;

import co.eltrut.differentiate.core.helper.BlockHelper;
import co.eltrut.differentiate.core.registrator.Registrator;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockSubRegistrator extends AbstractSubRegistrator<Block> {
	
	public BlockSubRegistrator(Registrator parent) {
		super(parent, ForgeRegistries.BLOCKS);
	}

	public BlockHelper create() {
		return new BlockHelper(this);
	}
	
	public RegistryObject<Block> createBlock(String name, Supplier<Block> block, ItemGroup group, String ...mods) {
		return new BlockHelper(this).setName(name).setBlock(block).setItemGroup(group).setMods(mods).build();
	}
	
}
