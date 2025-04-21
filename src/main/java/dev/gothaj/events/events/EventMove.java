package vip.gothaj.client.event.events;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventMove extends Event
{
    private double x;
    private double y;
    private double z;
}
