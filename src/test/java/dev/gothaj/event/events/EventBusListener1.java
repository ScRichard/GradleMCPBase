package dev.gothaj.event.events;

import dev.gothaj.events.EventType;
import dev.gothaj.events.annotations.Subscribe;
import dev.gothaj.events.events.EventMotion;
import dev.gothaj.events.events.EventUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventBusListener1 {

    private int counter = 0;

    private long timeStamp = 0;

    @Subscribe
    public void testEvent(EventMotion e) {
        counter++;
    }
    /*
     * This Event should be executed last
     */
    @Subscribe
    public void testEvent1(EventUpdate e) {
        timeStamp = System.nanoTime();
        if(e.getType() == EventType.PRE) {
            counter--;
        }else {
            counter++;
        }
    }

}
