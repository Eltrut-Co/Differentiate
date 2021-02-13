package co.eltrut.differentiate.core.helper;

import co.eltrut.differentiate.core.registrator.sub.ISubRegistrator;

public interface IHelper<T extends ISubRegistrator<?>> {

	T getParent();
	
}
