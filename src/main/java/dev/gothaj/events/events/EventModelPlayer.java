package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

@Getter
@Setter
@AllArgsConstructor
public class EventModelPlayer extends Event {
	private AbstractClientPlayer player;
	private ModelPlayer model;
}
