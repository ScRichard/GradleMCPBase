package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class EventMoveButton extends Event {

	private boolean left;
	private boolean right;
	private boolean backward;
	private boolean forward;
	private boolean sneak;
	private boolean jump;

}
