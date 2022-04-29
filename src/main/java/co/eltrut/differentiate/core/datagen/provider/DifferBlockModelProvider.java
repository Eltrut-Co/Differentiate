package co.eltrut.differentiate.core.datagen.provider;

import co.eltrut.differentiate.core.Differentiate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;

public abstract class DifferBlockModelProvider extends BlockStateProvider {
	private final DifferModelProvider provider;
	
	public DifferBlockModelProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
		super(gen, modid, exFileHelper);
		this.provider = new DifferModelProvider(gen, modid, exFileHelper) {
			@Override protected void registerModels() {}
		};
	}
	
	@Override
	public void run(HashCache cache) throws IOException {
		super.run(cache);
		provider.run(cache);
	}
	
//	public void verticalSlabBlock(VerticalSlabBlock block, ResourceLocation texture, ResourceLocation doubleslab) {
//		this.verticalSlabBlock(block, this.provider.verticalSlab(block.getRegistryName().getPath(), texture), this.models().getExistingFile(doubleslab));
//	}
//
//	public void verticalSlabBlock(VerticalSlabBlock block, ModelFile model, ModelFile doubleslab) {
//		this.getVariantBuilder(block)
//			.partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.NORTH).addModels(ConfiguredModel.builder().modelFile(model).rotationY(0).uvLock(true).build())
//			.partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.SOUTH).addModels(ConfiguredModel.builder().modelFile(model).rotationY(180).uvLock(true).build())
//			.partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.EAST).addModels(ConfiguredModel.builder().modelFile(model).rotationY(90).uvLock(true).build())
//			.partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.WEST).addModels(ConfiguredModel.builder().modelFile(model).rotationY(270).uvLock(true).build())
//			.partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.DOUBLE).addModels(new ConfiguredModel(doubleslab));
//	}
	
	public abstract static class DifferModelProvider extends BlockModelProvider {
		public DifferModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
			super(generator, modid, existingFileHelper);
		}

		public BlockModelBuilder verticalSlab(String name, ResourceLocation model) {
			return this.getBuilder(name)
					.parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Differentiate.MOD_ID, "block/vertical_slab")))
					.texture("side", model)
					.texture("bottom", model)
					.texture("top", model);
		}
	}
}