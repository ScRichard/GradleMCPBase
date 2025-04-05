package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public class EventPacketReceive extends Event {
    private Packet packet;

    private INetHandler iNetHandler;

    public Packet getPacket() {
        return this.packet;
    }

    public void setPacket(final Packet packet) {
        this.packet = packet;
    }

    public EventPacketReceive(final Packet packet, final INetHandler iNetHandler) {
        this.packet = packet;
        this.iNetHandler = iNetHandler;
    }

    public INetHandler getiNetHandler() {
        return iNetHandler;
    }

    public void setiNetHandler(INetHandler iNetHandler) {
        this.iNetHandler = iNetHandler;
    }
}
