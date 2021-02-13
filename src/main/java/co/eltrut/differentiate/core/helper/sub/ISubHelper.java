package co.eltrut.differentiate.core.helper.sub;

import co.eltrut.differentiate.core.helper.IHelper;

public interface ISubHelper<T extends IHelper<?>> {

	T getParent();
	
}
