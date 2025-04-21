package dev.gothaj.events.events;

import net.minecraft.network.Packet;
import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventSendPacket extends Event {
	
	private Packet packet;
	
}
