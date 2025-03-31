package dev.gothaj.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {

    private boolean cancelled;
    private EventType type = EventType.PRE;

}
