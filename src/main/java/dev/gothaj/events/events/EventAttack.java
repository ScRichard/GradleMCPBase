package dev.gothaj.events.events;

import net.minecraft.entity.Entity;


import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventAttack extends Event{
	
	private Entity entity;

}
