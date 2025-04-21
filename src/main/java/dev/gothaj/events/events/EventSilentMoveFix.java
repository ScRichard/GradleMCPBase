package vip.gothaj.client.event.events;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventSilentMoveFix extends Event {
	private float forward;
	private float strafe;
}
