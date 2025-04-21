package dev.gothaj.events.events;

import net.minecraft.util.BlockPos;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventBlockDamage extends Event {

	private float playerRelativeBlockHardness;
	private BlockPos pos;
	
}
