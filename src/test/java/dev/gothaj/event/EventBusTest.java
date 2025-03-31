package dev.gothaj.event;

import dev.gothaj.events.Event;
import dev.gothaj.events.EventBus;
import dev.gothaj.events.annotations.Subscribe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventBusTest {

    private EventBus eventBus;

    @BeforeEach
    public void init() {
        eventBus = new EventBus();
    }
    @Test
    public void register() {
        eventBus.register(this);
    }

    @Subscribe
    public final EventListener<Event> testEvent = (event) -> {
        System.out.println("Hello world");
    };

}
