package co.eltrut.differentiate.core.registrator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import co.eltrut.differentiate.common.interf.IColoredBlock;
import co.eltrut.differentiate.common.interf.IColoredItem;
import co.eltrut.differentiate.common.interf.ICompostableItem;
import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.common.interf.IRenderTypeBlock;
import co.eltrut.differentiate.common.interf.Interface;
import co.eltrut.differentiate.core.Differentiate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registrator {
	
	public static final List<Registrator> REGISTRATORS = new ArrayList<>();
	
	protected final String modid;
	protected final ItemHelper items;
	protected final BlockHelper blocks;
	protected final List<IHelper<?>> helpers;
	
	public Registrator(String modid) {
		this.modid = modid;
		this.items = new ItemHelper(this);
		this.blocks = new BlockHelper(this);
		this.helpers = new ArrayList<>(Arrays.asList(this.items, this.blocks));
		
		REGISTRATORS.add(this);
	}
	
	public Registrator(String modid, ItemHelper items, BlockHelper blocks) {
		this.modid = modid;
		this.items = items;
		this.blocks = blocks;
		this.helpers = new ArrayList<>(Arrays.asList(this.items, this.blocks));
		
		REGISTRATORS.add(this);
	}
	
	public void register(IEventBus bus) {
		this.items.register(bus);
		this.blocks.register(bus);
	}
	
	public static void registerCommon(final FMLCommonSetupEvent event) {
		registerBlockAttribute(ICompostableItem.class, s -> ComposterBlock.CHANCES.put(s.asItem(), ((ICompostableItem)s).getCompostableChance()));
		ForgeRegistries.ITEMS.getValues().stream()
				.filter(ICompostableItem.class::isInstance)
				.forEach(s -> ComposterBlock.CHANCES.put(s, ((ICompostableItem)s).getCompostableChance()));
		Differentiate.LOGGER.info("Registered compostables");
		
		registerBlockAttribute(IFlammableBlock.class, s -> ((FireBlock)Blocks.FIRE).setFireInfo(s, ((IFlammableBlock)s).getEncouragement(), ((IFlammableBlock)s).getFlammability()));
		Differentiate.LOGGER.info("Registered flammables");
	}
	
	public static void registerClient(final FMLClientSetupEvent event) {
		registerBlockAttribute(IRenderTypeBlock.class, s -> RenderTypeLookup.setRenderLayer(s, ((IRenderTypeBlock)s).getRenderType()));
		Differentiate.LOGGER.info("Registered cutouts");
		
		registerBlockAttribute(IColoredBlock.class, s -> {
					Minecraft.getInstance().getBlockColors().register(((IColoredBlock)s).getBlockColor(), s);
					Minecraft.getInstance().getItemColors().register(((IColoredBlock)s).getItemColor(), s);
					});
		ForgeRegistries.ITEMS.getValues().stream()
				.filter(IColoredItem.class::isInstance)
				.forEach(s -> Minecraft.getInstance().getItemColors().register(((IColoredItem)s).getItemColor(), s));
		Differentiate.LOGGER.info("Registered block and item colors");
	}
	
	public String getModId() {
		return this.modid;
	}
	
	public IHelper<?> getHelper(DeferredRegister<?> register) {
		for (IHelper<?> helper : this.helpers) {
			if (helper.getDeferredRegister().equals(register)) {
				return helper;
			}
		}
		return null;
	}
	
	public BlockHelper getBlockSubRegistrator() {
		return this.blocks;
	}
	
	public ItemHelper getItemSubRegistrator() {
		return this.items;
	}
	
	public static void registerBlockAttribute(Class<? extends Interface> clazz, Consumer<Block> consumer) {
		ForgeRegistries.BLOCKS.getValues().stream().filter(clazz::isInstance).forEach(consumer);
	}
	
}
