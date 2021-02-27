package co.eltrut.differentiate.core.util;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;

public class DataGenUtil {
	
	public static final String[] EMPTY = new String[0];
	
	public static void addProviders(DataGenerator generator, IDataProvider ...providers) {
		for (IDataProvider provider : providers) {
			generator.addProvider(provider);
		}
	}
	
}
