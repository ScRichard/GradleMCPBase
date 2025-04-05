package dev.gothaj.events.events;

import dev.gothaj.events.Event;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class EventBoundingBox extends Event {
    private Block block;
    private BlockPos pos;
    private AxisAlignedBB boundingBox;

    public Block getBlock() {
        return this.block;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }

    public void setBlock(final Block block) {
        this.block = block;
    }

    public void setPos(final BlockPos pos) {
        this.pos = pos;
    }

    public void setBoundingBox(final AxisAlignedBB boundingBox) {
        this.boundingBox = boundingBox;
    }

    public EventBoundingBox(final Block block, final BlockPos pos, final AxisAlignedBB boundingBox) {
        this.block = block;
        this.pos = pos;
        this.boundingBox = boundingBox;
    }
}
