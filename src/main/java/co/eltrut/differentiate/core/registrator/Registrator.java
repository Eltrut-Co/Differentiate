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
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderTypeLookup;
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
		this(modid, false);
	}
	
	public Registrator(String modid, boolean isCustom) {
		this.modid = modid;
		REGISTRATORS.add(this);
		
		if (!isCustom) {
			this.helpers.put(ForgeRegistries.ITEMS, new ItemHelper(this));
			this.helpers.put(ForgeRegistries.BLOCKS, new BlockHelper(this));
			this.helpers.put(ForgeRegistries.TILE_ENTITIES, new TileEntityHelper(this));
		}
	}
	
	public void register(IEventBus bus) {
		for (IHelper<?> helper : this.helpers.values()) {
			helper.register(bus);
		}
	}
	
	public static void registerCommon(final FMLCommonSetupEvent event) {
		registerAttribute(ForgeRegistries.BLOCKS, ICompostableItem.class, s -> ComposterBlock.CHANCES.put(s.asItem(), ((ICompostableItem)s).getCompostableChance()));
		registerAttribute(ForgeRegistries.ITEMS, ICompostableItem.class, s -> ComposterBlock.CHANCES.put(s, ((ICompostableItem)s).getCompostableChance()));
		LOGGER.info("Registered block and item compostables");
		
		registerAttribute(ForgeRegistries.BLOCKS, IFlammableBlock.class, s -> ((FireBlock)Blocks.FIRE).setFireInfo(s, ((IFlammableBlock)s).getEncouragement(), ((IFlammableBlock)s).getFlammability()));
		LOGGER.info("Registered block flammables");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void registerClient(final FMLClientSetupEvent event) {
		registerAttribute(ForgeRegistries.BLOCKS, IRenderTypeBlock.class, s -> RenderTypeLookup.setRenderLayer(s, ((IRenderTypeBlock)s).getRenderType()));
		LOGGER.info("Registered block cutouts");
		
		registerAttribute(ForgeRegistries.BLOCKS, IColoredBlock.class, s -> {
					Minecraft.getInstance().getBlockColors().register(((IColoredBlock)s).getBlockColor(), s);
					Minecraft.getInstance().getItemColors().register(((IColoredBlock)s).getItemColor(), s);
					});
		registerAttribute(ForgeRegistries.ITEMS, IColoredItem.class, s -> Minecraft.getInstance().getItemColors().register(((IColoredItem)s).getItemColor(), s));
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
	
}
