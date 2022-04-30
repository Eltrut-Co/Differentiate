package co.eltrut.test.core.datagen;

import co.eltrut.differentiate.core.datagen.provider.DifferBlockModelProvider;
import co.eltrut.test.core.Test;
import co.eltrut.test.core.registry.TestBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockModels extends DifferBlockModelProvider {
	public BlockModels(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
		super(gen, modid, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.simpleBlockItem(TestBlocks.BLOCK.get(), new ModelFile.UncheckedModelFile(new ResourceLocation(Test.MOD_ID, "block/test")));
		this.slabBlock((SlabBlock)TestBlocks.BLOCK.get(), new ResourceLocation(Test.MOD_ID, "block/test"), new ResourceLocation(Test.MOD_ID, "block/test"));
	}
}