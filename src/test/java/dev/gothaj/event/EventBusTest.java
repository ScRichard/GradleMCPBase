package dev.gothaj.event;

import dev.gothaj.event.events.EventBusListener1;
import dev.gothaj.event.events.EventBusListener2;
import dev.gothaj.events.EventBus;
import dev.gothaj.events.EventType;
import dev.gothaj.events.events.EventMotion;
import dev.gothaj.events.events.EventUpdate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventBusTest {

    protected EventBus eventBus;
    protected EventBusListener1 listener;
    protected EventBusListener2 listener2;

    @BeforeEach
    void init() {
        eventBus = new EventBus();
        listener = new EventBusListener1();
        listener2 = new EventBusListener2();
        eventBus.registerPriorities();
    }

    /*
    * Register and Unregister test adding to counter 1 and -1
    */
    @Test
    void registerAndUnregister() {

        eventBus.register(listener);

        long milis = System.nanoTime();
        eventBus.fire(new EventMotion());
        System.out.println("Speed Execution: " + (System.nanoTime() - milis));

        Assertions.assertEquals(1, listener.getCounter(), "Event should be received after registration 1");

        eventBus.unregister(listener);

        eventBus.fire(new EventMotion());

        Assertions.assertEquals(1, listener.getCounter(), "Event still be 1");
    }
    /*
     * Trying if works PRE and POST setting
     */
    @Test
    void EventPreAndPostTest() {

        eventBus.register(listener);

        // Default Event is PRE
        EventUpdate event = new EventUpdate();
        eventBus.fire(event);

        // Setting event to post
        event.setType(EventType.POST);
        eventBus.fire(event);

        Assertions.assertEquals(0, listener.getCounter(), "Event should be called and counter +1");
    }

    /*
     * Testing the priority Executed first and last
     */
    @Test
    void EventExecutionPriority() {

        eventBus.register(listener);
        eventBus.register(listener2);

        eventBus.fire(new EventUpdate());

        Assertions.assertTrue(listener.getTimeStamp() > listener2.getTimeStamp(), "Listener2 Event should be called earlier");

    }
}
