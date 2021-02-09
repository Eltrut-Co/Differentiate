package co.eltrut.differentiate.core.helper;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class AbstractHelper<T extends IForgeRegistryEntry<T>> implements IHelper<T> {
	
	protected final String MODID;
	protected final DeferredRegister<T> REGISTRY;
	
	public AbstractHelper(String modid, IForgeRegistry<T> registry) {
		this.MODID = modid;
		this.REGISTRY = DeferredRegister.create(registry, modid);
	}
	
	public String getModId() {
		return this.MODID;
	}
	
	public DeferredRegister<T> getDeferredRegister() {
		return this.REGISTRY;
	}
	
}
