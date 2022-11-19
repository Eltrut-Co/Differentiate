package co.eltrut.differentiate.platform;

import co.eltrut.differentiate.platform.common.helpers.BlockEntityHelper;
import co.eltrut.differentiate.platform.common.helpers.BlockHelper;
import co.eltrut.differentiate.platform.common.helpers.EntityHelper;
import co.eltrut.differentiate.platform.common.helpers.ItemHelper;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;

import java.util.function.Supplier;

public abstract class Helper<T> {
    public final Registry<T> registry;
    public final String modId;
    protected boolean isPresent;

    protected Helper(Registry<T> registry, String modId) {
        this.registry = registry;
        this.modId = modId;
        this.isPresent = false;
    }

    @ExpectPlatform
    public static <T> Helper<T> create(Registry<T> registry, String modId) {
        throw new AssertionError();
    }

    public static ItemHelper createItem(String modId) {
        return new ItemHelper(create(Registry.ITEM, modId));
    }

    public static BlockHelper createBlock(String modId) {
        return new BlockHelper(create(Registry.BLOCK, modId));
    }

    public static EntityHelper createEntity(String modId) {
        return new EntityHelper(create(Registry.ENTITY_TYPE, modId));
    }

    public static BlockEntityHelper createBlockEntity(String modId) {
        return new BlockEntityHelper(create(Registry.BLOCK_ENTITY_TYPE, modId));
    }

    public abstract <E extends T> Supplier<E> register(String key, Supplier<E> entry);

    public void register() {
        if (this.isPresent) {
            throw new IllegalStateException("Duplication of Registry: " + this.registry);
        }
        this.isPresent = true;
        this.load();
    }

    public abstract void load();
}