package dev.gothaj.event.events;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventMotion extends Event {
    double x,y,z;
    boolean onground;
    float yaw,pitch;
}
