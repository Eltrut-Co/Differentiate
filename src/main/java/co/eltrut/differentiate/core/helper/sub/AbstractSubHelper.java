package co.eltrut.differentiate.core.helper.sub;

import co.eltrut.differentiate.core.helper.IHelper;

public class AbstractSubHelper<T extends IHelper<?>> implements ISubHelper<T> {

	protected T parent;
	
	public AbstractSubHelper(T parent) {
		this.parent = parent;
	}
	
	@Override
	public T getParent() {
		return this.parent;
	}

}
