package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventMoveFlying extends Event {
	private float strafe;
	private float forward;
	private float friction;
	private float yaw;
}
