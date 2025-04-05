package dev.gothaj.utilities.font.initializers;

import net.minecraft.util.ResourceLocation;

import java.awt.*;

public interface FontManagerInitializer {
    Font getResource(ResourceLocation fontFile, float size);
}
