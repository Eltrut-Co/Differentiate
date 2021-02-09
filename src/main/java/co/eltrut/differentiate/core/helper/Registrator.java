package co.eltrut.differentiate.core.helper;

import net.minecraftforge.eventbus.api.IEventBus;

public class Registrator {

	protected final String MODID;
	protected final ItemHelper ITEM_HELPER;
	protected final BlockHelper BLOCK_HELPER;
	
	public Registrator(String modid) {
		this.MODID = modid;
		
		this.ITEM_HELPER = new ItemHelper(modid);
		this.BLOCK_HELPER = new BlockHelper(modid, this.ITEM_HELPER);
	}
	
	public void register(IEventBus eventBus) {
		this.ITEM_HELPER.getDeferredRegister().register(eventBus);
		this.BLOCK_HELPER.getDeferredRegister().register(eventBus);
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
