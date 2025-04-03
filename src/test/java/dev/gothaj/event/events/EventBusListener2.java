package dev.gothaj.event.events;

import dev.gothaj.events.EventPriority;
import dev.gothaj.events.EventType;
import dev.gothaj.events.annotations.Subscribe;
import dev.gothaj.events.events.EventMotion;
import dev.gothaj.events.events.EventUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventBusListener2 {

    private int counter = 0;
    private long timeStamp = 0;

    @Subscribe
    public void testEvent(EventMotion e) {
        counter++;
    }
    /*
    * This Event should be executed first
    */
    @Subscribe(priority = EventPriority.LOW)
    public void testEvent2(EventUpdate e) {
        timeStamp = System.nanoTime();
        if(e.getType() == EventType.PRE) {
            counter--;
        }else {
            counter++;
        }
    }

}
