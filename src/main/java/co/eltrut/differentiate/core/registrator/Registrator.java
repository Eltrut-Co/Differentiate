package co.eltrut.differentiate.core.registrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.eltrut.differentiate.common.interf.IColoredBlock;
import co.eltrut.differentiate.common.interf.IColoredItem;
import co.eltrut.differentiate.common.interf.ICompostableItem;
import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.common.interf.IRenderTypeBlock;
import co.eltrut.differentiate.common.interf.IRendererTileEntity;
import co.eltrut.differentiate.common.interf.Interface;
import co.eltrut.differentiate.core.util.DataUtil;
import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Registrator {
	
	private static final Logger LOGGER = LogManager.getLogger();
	public static final List<Registrator> REGISTRATORS = new ArrayList<>();
	
	private final String modid;
	private final Map<IForgeRegistry<?>, IHelper<?>> helpers = new HashMap<>();
	
	public Registrator(String modid) {
		this.modid = modid;
		REGISTRATORS.add(this);
		
		this.helpers.put(ForgeRegistries.ITEMS, new ItemHelper(this));
		this.helpers.put(ForgeRegistries.BLOCKS, new BlockHelper(this));
		this.helpers.put(ForgeRegistries.TILE_ENTITIES, new TileEntityHelper(this));
	}
	
	public static Registrator create(String modid, Consumer<Registrator> consumer) {
		Registrator registrator = new Registrator(modid);
		consumer.accept(registrator);
		return registrator;
	}
	
	public void register(IEventBus bus) {
		for (IHelper<?> helper : this.helpers.values()) {
			helper.register(bus);
		}
	}
	
	public static void registerCommon(final FMLCommonSetupEvent event) {
		registerAttribute(ForgeRegistries.BLOCKS, ICompostableItem.class, Registrator::registerCompostable);
		registerAttribute(ForgeRegistries.ITEMS, ICompostableItem.class, Registrator::registerCompostable);
		LOGGER.info("Registered block and item compostables");
		
		registerAttribute(ForgeRegistries.BLOCKS, IFlammableBlock.class, Registrator::registerFlammable);
		LOGGER.info("Registered block flammables");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void registerClient(final FMLClientSetupEvent event) {
		registerAttribute(ForgeRegistries.BLOCKS, IRenderTypeBlock.class, Registrator::registerCutout);
		LOGGER.info("Registered block cutouts");
		
		registerAttribute(ForgeRegistries.BLOCKS, IColoredBlock.class, Registrator::registerBlockColor);
		registerAttribute(ForgeRegistries.ITEMS, IColoredItem.class, Registrator::registerItemColor);
		LOGGER.info("Registered block and item colors");
		
		registerAttribute(ForgeRegistries.TILE_ENTITIES, IRendererTileEntity.class, s -> ClientRegistry.bindTileEntityRenderer(s, ((IRendererTileEntity)s).getRendererFactory()));
		LOGGER.info("Registered tile entity renderers");
	}
	
	public String getModId() {
		return this.modid;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends IHelper<?>> T getHelper(IForgeRegistry<?> registry) {
		return (T) this.helpers.get(registry);
	}
	
	public Map<IForgeRegistry<?>, IHelper<?>> getHelpers() {
		return this.helpers;
	}
	
	public static <T extends IForgeRegistryEntry<T>> void registerAttribute(IForgeRegistry<T> registry, Class<? extends Interface> clazz, Consumer<T> consumer) {
		registry.getValues().stream().filter(clazz::isInstance).forEach(consumer);
	}
	
	private static void registerCompostable(IItemProvider item) {
		ICompostableItem compostableItem = (ICompostableItem)item;
		DataUtil.registerCompostable(item, compostableItem.getCompostableChance());
	}
	
	private static void registerFlammable(Block block) {
		IFlammableBlock flammableBlock = (IFlammableBlock)block;
		DataUtil.registerFlammable(block, flammableBlock.getEncouragement(), flammableBlock.getFlammability());
	}
	
	private static void registerCutout(Block block) {
		IRenderTypeBlock renderTypeBlock = (IRenderTypeBlock)block;
		DataUtil.registerCutout(block, renderTypeBlock.getRenderType());
	}
	
	private static void registerBlockColor(Block block) {
		IColoredBlock coloredBlock = (IColoredBlock)block;
		DataUtil.registerBlockColor(coloredBlock.getBlockColor(), block);
	}
	
	private static void registerItemColor(IItemProvider item) {
		IColoredItem coloredItem = (IColoredItem)item;
		DataUtil.registerItemColor(coloredItem.getItemColor(), item);
	}
	
}