package dev.gothaj.events.events;

import dev.gothaj.events.Event;

public class EventItemHotbarSwitch extends Event {

    public int slot;

    public EventItemHotbarSwitch(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
