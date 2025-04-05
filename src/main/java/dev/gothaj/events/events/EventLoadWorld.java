package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import lombok.Getter;
import net.minecraft.client.multiplayer.WorldClient;

@Getter
public class EventLoadWorld extends Event {

    private WorldClient worldClient;

    public EventLoadWorld(WorldClient worldClient) {
        this.worldClient = worldClient;
    }

}
