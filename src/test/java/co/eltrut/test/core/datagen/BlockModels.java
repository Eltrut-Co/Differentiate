package co.eltrut.test.core.datagen;

import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.core.datagen.provider.DifferBlockModelProvider;
import co.eltrut.test.core.Test;
import co.eltrut.test.core.registry.TestBlocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
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
		this.verticalSlabBlock((VerticalSlabBlock)TestBlocks.SLABBY.get(), new ResourceLocation(Test.MOD_ID, "block/test"), new ResourceLocation(Test.MOD_ID, "block/test"));
	}

}
