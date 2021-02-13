package co.eltrut.differentiate.core.helper;

import co.eltrut.differentiate.core.registrator.sub.ISubRegistrator;

public class AbstractHelper<T extends ISubRegistrator<?>> implements IHelper<T> {
	
	protected T parent;
	
	public AbstractHelper(T parent) {
		this.parent = parent;
	}
	
	public T getParent() {
		return this.parent;
	}
	
}
