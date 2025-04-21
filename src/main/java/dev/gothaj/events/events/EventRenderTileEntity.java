package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.tileentity.TileEntity;

@Getter
@Setter
@AllArgsConstructor
public class EventRenderTileEntity extends Event {
	private TileEntity entity;
}
