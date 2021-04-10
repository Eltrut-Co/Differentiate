package co.eltrut.test.core.datagen;

import co.eltrut.test.core.Test;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Bus.MOD)
public class DataGenerators {
	
	@SubscribeEvent
	public static void gatherData(final GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();
		//generator.addProvider(new Recipes(generator));
		generator.addProvider(new BlockModels(generator, Test.MOD_ID, helper));
		generator.addProvider(new ItemModels(generator, Test.MOD_ID, helper));
	}
	
}
