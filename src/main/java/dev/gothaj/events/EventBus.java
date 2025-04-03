package dev.gothaj.events;

import dev.gothaj.events.annotations.Subscribe;
import dev.gothaj.events.initializers.EventBusInitializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class EventBus implements EventBusInitializer {

    private final Map<EventPriority, Map<Object, Method>> elements = new TreeMap<>();

    /*
    * Launching an event to modules
    */
    @Override
    public void fire(Event event) {
        for (Map<Object, Method> priorities : elements.values()) {
            for (Map.Entry<Object, Method> entry : priorities.entrySet()) {

                this.invokeMethod(entry.getKey(), entry.getValue(), event);
            }
        }
    }
    /*
     * Invoking method and checking parameter
     */
    @Override
    public void invokeMethod(Object o, Method method, Event event) {

        if(!this.isParameterEvent(method.getParameterTypes()[0], event))
            return;
        try {
            method.invoke(o, event);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * Checking parameter if its event
     */
    @Override
    public boolean isParameterEvent(Class<?> parameter, Event event) {
        return parameter.equals(event.getClass());
    }

    /*
     * Registring an Object to listeners
     */
    @Override
    public void register(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (!(method.isAnnotationPresent(Subscribe.class) && method.getParameterCount() == 1))
                continue;

            EventPriority priority = method.getAnnotation(Subscribe.class).priority();
            elements.computeIfAbsent(priority, k -> new HashMap<>()).put(listener, method);
        }
    }
    /*
     * Unregistring object from listeners
     */
    @Override
    public void unregister(Object listener) {
        elements.values().forEach(map -> map.remove(listener));
    }
    /*
     * Registration of priorities of launching
     */
    @Override
    public void registerPriorities() {
        elements.clear();
        for (EventPriority priority : EventPriority.values()) {
            elements.put(priority, new HashMap<>());
        }
    }
}
