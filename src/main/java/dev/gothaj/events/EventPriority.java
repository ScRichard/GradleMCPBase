package dev.gothaj.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventPriority {
    LOWEST(0),
    LOW(100),
    MEDIUM(200),
    HIGH(300),
    HIGHEST(400);

    private long value;

}
