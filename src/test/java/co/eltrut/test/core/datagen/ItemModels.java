package co.eltrut.test.core.datagen;

import java.util.function.Supplier;

import co.eltrut.test.core.registry.TestItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {

	public ItemModels(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
		super(generator, modid, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		this.simpleItem(TestItems.ITEM);
	}
	
	public void simpleItem(Supplier<? extends Item> item) {
		ResourceLocation loc = item.get().getRegistryName();
		this.getBuilder(loc.getPath())
			.parent(new ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", new ResourceLocation(loc.getNamespace(), ITEM_FOLDER + "/" + loc.getPath()));
	}

}
