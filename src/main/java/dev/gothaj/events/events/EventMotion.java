package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventMotion extends Event {

    private double x;
    private double y;
    private double z;

    private float yaw;
    private float pitch;
    private boolean onGround;

}
