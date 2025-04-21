package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventKnockBack extends Event {

	private double motion = 0.6;
	private boolean full;
	private boolean	strong;
	private boolean reduceY;

	private int power;
	private int postPower;
}
