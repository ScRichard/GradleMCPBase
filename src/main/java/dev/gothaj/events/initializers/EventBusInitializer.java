package dev.gothaj.events.initializers;

import dev.gothaj.events.Event;

import java.lang.reflect.Method;

public interface EventBusInitializer {
    void fire(Event e);

    void invokeMethod(Object o, Method method, Event event);

    boolean isParameterEvent(Class<?> parameter, Event event);

    void register(Object o);
    void unregister(Object o);

    void registerPriorities();
}
