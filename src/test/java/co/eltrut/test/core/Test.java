package co.eltrut.test.core;

import co.eltrut.differentiate.core.registrator.DifferHelper;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.ArrayUtils;

import co.eltrut.differentiate.core.datagen.Generator;
import co.eltrut.differentiate.removal.registrator.Registrator;
import co.eltrut.differentiate.core.util.CompatUtil.Mods;
import co.eltrut.differentiate.core.util.RecipeUtil;
import co.eltrut.test.core.registry.TestBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod("test")
@Mod.EventBusSubscriber(modid = "test", bus = Bus.MOD)
public class Test {
    public static final String MOD_ID = "test";
    public static final DifferHelper<Block> REGISTRATOR = DifferHelper.create(ForgeRegistries.BLOCKS, MOD_ID);
	public static Test instance;

	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

	public Test() {
	    modEventBus.addListener(this::doCommonStuff);
	    modEventBus.addListener(this::doClientStuff);
	    modEventBus.addListener(this::doDataStuff);
	    instance = this;

		REGISTRATOR.register(modEventBus);
	    MinecraftForge.EVENT_BUS.register(this);
	       
	}
	
	public void doCommonStuff(final FMLCommonSetupEvent event) {}
	
	public void doClientStuff(final FMLClientSetupEvent event) {}
	
	public void doDataStuff(final GatherDataEvent event) {
		Generator generator = new Generator(event.getGenerator(), event.getExistingFileHelper(), MOD_ID);
		generator.addRecipe(s -> {RecipeUtil.shapedRecipe(Blocks.DIAMOND_BLOCK)
        	.patternLine("xxx")
        	.patternLine("x#x")
        	.patternLine("xxx")
        	.key('x', Blocks.COBBLESTONE)
        	.key('#', Tags.Items.DYES_RED)
        	.setGroup("mytutorial")
        	.addModCompat(Mods.QUARK, Mods.COOKIELICIOUS)
        	.addConditions("honey_cookie_tiles")
        	.build(s);
		});
		generator.addRecipe(s -> {
			RecipeUtil.slabCraftingRecipe(s, TestBlocks.BLOCK.get(), TestBlocks.BLOCK_TWO.get(), ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
			RecipeUtil.verticalSlabCraftingRecipe(s, TestBlocks.BLOCK_TWO.get(), TestBlocks.BLOCK_THREE.get(), new String[] {Mods.LEPTON}, new String[] {"honey_cookie_tiles", "strawberry_cookie_tiles"}, new String[] {"biotite"});
		});
		
		generator.run();
	}
	    
}