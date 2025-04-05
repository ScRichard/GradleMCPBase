package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRayTrace extends Event {
    private float range;
    private float blockRange;

    public float getRange() {
        return this.range;
    }

    public float getBlockRange() {
        return this.blockRange;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public void setBlockRange(float blockRange) {
        this.blockRange = blockRange;
    }

    public EventRayTrace(float range, float blockRange) {
        this.range = range;
        this.blockRange = blockRange;
    }
}
