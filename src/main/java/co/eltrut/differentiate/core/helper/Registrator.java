package co.eltrut.differentiate.core.helper;

import java.util.ArrayList;

import co.eltrut.differentiate.common.blocks.interf.ICompostableItem;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class Registrator {
	
	public static final ArrayList<Registrator> REGISTRATORS = new ArrayList<>();

	protected final String MODID;
	protected final ItemHelper ITEM_HELPER;
	protected final BlockHelper BLOCK_HELPER;
	
	public Registrator(String modid) {
		this.MODID = modid;
		
		this.ITEM_HELPER = new ItemHelper(this);
		this.BLOCK_HELPER = new BlockHelper(this);
		
		REGISTRATORS.add(this);
	}
	
	public void register(IEventBus bus) {
		this.ITEM_HELPER.register(bus);
		this.BLOCK_HELPER.register(bus);
	}
	
	public void registerCompostables(final FMLCommonSetupEvent event) {
		for (RegistryObject<Block> blockObject : this.BLOCK_HELPER.REGISTRY.getEntries()) {
			if (blockObject.get() instanceof ICompostableItem) {
				ComposterBlock.CHANCES.put(blockObject.get().asItem(), ((ICompostableItem)blockObject.get()).compostableChance());
			}
		}
	}
	
	public String getModId() {
		return this.MODID;
	}
	
	public BlockHelper getBlockHelper() {
		return this.BLOCK_HELPER;
	}
	
	public ItemHelper getItemHelper() {
		return this.ITEM_HELPER;
	}
	
}
