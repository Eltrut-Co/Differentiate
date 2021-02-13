package co.eltrut.differentiate.core.helper;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.item.DifferBlockItem;
import co.eltrut.differentiate.core.helper.sub.BlockItemHelper;
import co.eltrut.differentiate.core.registrator.sub.BlockSubRegistrator;
import co.eltrut.differentiate.core.registrator.sub.ItemSubRegistrator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class BlockHelper extends AbstractHelper<BlockSubRegistrator> {
	
	private String name;
	private Supplier<Block> block;
	private BlockItemHelper helper;
	
	public BlockHelper(BlockSubRegistrator parent) {
		super(parent);
	}
	
	public BlockHelper setName(String name) {
		this.name = name;
		return this;
	}
	
	public BlockHelper setBlock(Supplier<Block> block) {
		this.block = block;
		return this;
	}
	
	public BlockItemHelper setItem() {
		this.helper = new BlockItemHelper(this);
		return this.helper;
	}
	
	public RegistryObject<Block> build() {
		RegistryObject<Block> registeredBlock = this.parent.getDeferredRegister().register(this.name, this.block);
		ItemSubRegistrator itemRegister = this.parent.getParent().getItemSubRegistrator();
		Item.Properties props = new Item.Properties().group(this.helper.getDeterminedItemGroup());
		itemRegister.createItem(this.name, () -> new DifferBlockItem(registeredBlock.get(), props, this.helper.getBurnTime()));
		return registeredBlock;
	}
	
}
