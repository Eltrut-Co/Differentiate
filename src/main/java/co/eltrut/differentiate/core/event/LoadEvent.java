package co.eltrut.differentiate.core.event;

import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;

import java.util.function.Consumer;

public final class LoadEvent extends Event implements IEventBus {
    @Override
    public void register(Object target) {

    }

    @Override
    public <T extends Event> void addListener(Consumer<T> consumer) {

    }

    @Override
    public <T extends Event> void addListener(Class<T> eventType, Consumer<T> consumer) {

    }

    @Override
    public <T extends Event> void addListener(EventPriority priority, Consumer<T> consumer) {

    }

    @Override
    public <T extends Event> void addListener(EventPriority priority, Class<T> eventType, Consumer<T> consumer) {

    }

    @Override
    public <T extends Event> void addListener(EventPriority priority, boolean receiveCanceled, Consumer<T> consumer) {

    }

    @Override
    public <T extends Event> void addListener(EventPriority priority, boolean receiveCanceled, Class<T> eventType, Consumer<T> consumer) {

    }

    @Override
    public <T extends Event> void addListener(boolean receiveCanceled, Consumer<T> consumer) {

    }

    @Override
    public <T extends Event> void addListener(boolean receiveCanceled, Class<T> eventType, Consumer<T> consumer) {

    }

    @Override
    public void unregister(Object object) {

    }

    @Override
    public <T extends Event> T post(T event) {
        return null;
    }

    @Override
    public <T extends Event> T post(EventPriority phase, T event) {
        return null;
    }

    @Override
    public void start() {

    }
}
