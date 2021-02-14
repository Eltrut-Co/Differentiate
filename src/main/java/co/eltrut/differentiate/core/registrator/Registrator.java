package co.eltrut.differentiate.core.registrator;

import java.util.ArrayList;

import co.eltrut.differentiate.common.interf.ICompostableItem;
import co.eltrut.differentiate.common.interf.IRenderTypeBlock;
import co.eltrut.differentiate.core.registrator.sub.BlockSubRegistrator;
import co.eltrut.differentiate.core.registrator.sub.ItemSubRegistrator;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class Registrator {
	
	public static final ArrayList<Registrator> REGISTRATORS = new ArrayList<>();
	
	protected final String modid;
	protected final ItemSubRegistrator items;
	protected final BlockSubRegistrator blocks;
	
	public Registrator(String modid) {
		this.modid = modid;
		
		this.items = new ItemSubRegistrator(this);
		this.blocks = new BlockSubRegistrator(this);
		
		REGISTRATORS.add(this);
	}
	
	public void register(IEventBus bus) {
		this.items.register(bus);
		this.blocks.register(bus);
	}
	
	public void registerCommon(final FMLCommonSetupEvent event) {
		this.blocks.getDeferredRegister().getEntries().stream()
				.map(s -> s.get())
				.filter(s -> (s instanceof ICompostableItem))
				.forEach(s -> ComposterBlock.CHANCES.put(s.asItem(), ((ICompostableItem)s).getCompostableChance()));
	}
	
	public void registerClient(final FMLClientSetupEvent event) {
		this.blocks.getDeferredRegister().getEntries().stream()
				.map(s -> s.get())
				.filter(s -> (s instanceof IRenderTypeBlock))
				.forEach(s -> RenderTypeLookup.setRenderLayer(s, ((IRenderTypeBlock)s).getRenderType()));
	}
	
	public String getModId() {
		return this.modid;
	}
	
	public BlockSubRegistrator getBlockSubRegistrator() {
		return this.blocks;
	}
	
	public ItemSubRegistrator getItemSubRegistrator() {
		return this.items;
	}
	
}
