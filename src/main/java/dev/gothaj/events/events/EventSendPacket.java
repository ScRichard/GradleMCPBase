package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import net.minecraft.network.Packet;

public class EventSendPacket extends Event {
    final Packet<?> p;

    public EventSendPacket(Packet<?> p) {
        this.p = p;
    }

    public <T extends Packet<?>> T getPacket() {
        return (T)this.p;
    }
}
