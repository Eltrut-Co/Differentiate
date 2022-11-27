package co.eltrut.differentiate.common.capability;

@FunctionalInterface
public interface CapabilityFactory<C> {
    C createComponent(Object t);
}