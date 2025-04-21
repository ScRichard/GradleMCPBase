package dev.gothaj.events.events;

import net.minecraft.util.BlockPos;
import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventBlockBreak extends Event {
	private BlockPos location;
}
