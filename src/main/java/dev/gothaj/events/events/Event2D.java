package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.ScaledResolution;

@Getter
@Setter
@AllArgsConstructor
public class Event2D extends Event {

    public ScaledResolution sr;

    public float partialTicks;
}
