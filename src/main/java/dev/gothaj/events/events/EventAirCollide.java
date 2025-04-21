
package dev.gothaj.events.events;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;


import dev.gothaj.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventAirCollide extends Event {

	private AxisAlignedBB returnValue;
	private World worldIn;
	private BlockPos pos;
	private IBlockState state;

	private double minX;
	private double minY;
	private double minZ;
	private double maxX;
	private double maxY;
	private double maxZ;
	
}
