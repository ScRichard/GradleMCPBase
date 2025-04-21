package dev.gothaj.events.events;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventRenderHotbar extends Event{
	private GuiIngame guiIngame;
	private float partialTicks;
	private ScaledResolution sr;
}
