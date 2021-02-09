package co.eltrut.differentiate.core.helper;

import net.minecraftforge.eventbus.api.IEventBus;

public class Registrator {

	protected final String MODID;
	protected final ItemHelper ITEM_HELPER;
	protected final BlockHelper BLOCK_HELPER;
	
	public Registrator(String modid) {
		this.MODID = modid;
		
		this.ITEM_HELPER = new ItemHelper(this);
		this.BLOCK_HELPER = new BlockHelper(this);
	}
	
	public void register(IEventBus bus) {
		this.ITEM_HELPER.register(bus);
		this.BLOCK_HELPER.register(bus);
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
