package co.eltrut.differentiate.core.registrator;

import java.util.ArrayList;

import co.eltrut.differentiate.common.interf.IColoredBlock;
import co.eltrut.differentiate.common.interf.IColoredItem;
import co.eltrut.differentiate.common.interf.ICompostableItem;
import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.common.interf.IRenderTypeBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

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
	
	public static void registerCommon(final FMLCommonSetupEvent event) {
		ForgeRegistries.BLOCKS.getValues().stream()
				.filter(ICompostableItem.class::isInstance)
				.forEach(s -> ComposterBlock.CHANCES.put(s.asItem(), ((ICompostableItem)s).getCompostableChance()));
		ForgeRegistries.ITEMS.getValues().stream()
				.filter(ICompostableItem.class::isInstance)
				.forEach(s -> ComposterBlock.CHANCES.put(s, ((ICompostableItem)s).getCompostableChance()));
		
		ForgeRegistries.BLOCKS.getValues().stream()
				.filter(IFlammableBlock.class::isInstance)
				.forEach(s -> ((FireBlock)Blocks.FIRE).setFireInfo(s, ((IFlammableBlock)s).getEncouragement(), ((IFlammableBlock)s).getFlammability()));
	}
	
	public static void registerClient(final FMLClientSetupEvent event) {
		ForgeRegistries.BLOCKS.getValues().stream()
				.filter(IRenderTypeBlock.class::isInstance)
				.forEach(s -> RenderTypeLookup.setRenderLayer(s, ((IRenderTypeBlock)s).getRenderType()));
		
		ForgeRegistries.BLOCKS.getValues().stream()
				.filter(IColoredBlock.class::isInstance)
				.forEach(s -> {
					Minecraft.getInstance().getBlockColors().register(((IColoredBlock)s).getBlockColor(), s);
					Minecraft.getInstance().getItemColors().register(((IColoredBlock)s).getItemColor(), s);
					});
		ForgeRegistries.ITEMS.getValues().stream()
				.filter(IColoredItem.class::isInstance)
				.forEach(s -> Minecraft.getInstance().getItemColors().register(((IColoredItem)s).getItemColor(), s));
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
	
//	public static <T extends ISubRegistrator<?>, U extends Interface> Stream<?> stream(T subregistrator, Class<U> clazz) {
//		return subregistrator.getDeferredRegister().getEntries().stream()
//				.map(s -> s.get())
//				.filter(clazz::isInstance);
//	}
	
}
