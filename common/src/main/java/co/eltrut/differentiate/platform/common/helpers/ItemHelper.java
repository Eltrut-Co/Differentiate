package co.eltrut.differentiate.platform.common.helpers;

import co.eltrut.differentiate.platform.Helper;
import co.eltrut.differentiate.platform.common.utility.BoatUtil;
import co.eltrut.differentiate.platform.common.utility.CustomBoatType;
import co.eltrut.differentiate.platform.common.utility.TabUtil;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ItemHelper extends Helper<Item> {
	private final Helper<Item> helper;

	public ItemHelper(Helper<Item> helper) {
		super(helper.registry, helper.modId);
		this.helper = helper;
	}

	public Supplier<Item> createItem(String id, Supplier<Item> item) {
		return this.helper.register(id, item);
	}

	public Supplier<Item> createItemWithTab(String id, CreativeModeTab tab, String mods) {
		return this.createItem(id, () -> new Item(TabUtil.getProps(tab, mods)));
	}

	public Supplier<Item> createFuelItem(String id, CreativeModeTab tab, int length, String mods) {
		Supplier<Item> item = this.createItem(id, () -> new Item(TabUtil.getProps(tab, mods)));
		FuelHelper.registerFuel(item.get(), length);
		return item;
	}

	public Pair<Supplier<Item>, Supplier<Item>> createBoatAndChestBoatItem(String id, Block block) {
//		ItemHelper.createBoatType(id, block);
		Supplier<Item> boat = this.createItem(id + "_boat", () -> new BoatItem(false, Boat.Type.byName(id), new Item.Properties()));
		Supplier<Item> chestBoat = this.createItem(id + "_chest_boat", () -> new BoatItem(true, Boat.Type.byName(id), new Item.Properties()));

		return Pair.of(boat, chestBoat);
	}

	@Override
	public <E extends Item> Supplier<E> register(String key, Supplier<E> entry) {
		return helper.register(key, entry);
	}

	@Override
	public void load() {
		helper.load();
	}
}