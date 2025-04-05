package dev.gothaj.utilities.client;

import net.minecraft.util.ResourceLocation;

public class Resources {

    private Resources() {
        throw new IllegalStateException("Utility class");
    }
    // Shaders
    public static final ResourceLocation roundedRectShader = new ResourceLocation("gothaj/shaders/roundedRect.frag");

    //Fonts
    public static final ResourceLocation robotoFont = new ResourceLocation("gothaj/fonts/Roboto_Condensed-Regular.ttf");

}
