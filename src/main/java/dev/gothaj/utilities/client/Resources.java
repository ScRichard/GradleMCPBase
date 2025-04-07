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
    public static final ResourceLocation robotoMediumFont = new ResourceLocation("gothaj/fonts/Roboto_Condensed-Medium.ttf");
    public static final ResourceLocation robotoBoldFont = new ResourceLocation("gothaj/fonts/Roboto_Condensed-Bold.ttf");
    public static final ResourceLocation robotoExtraBoldFont = new ResourceLocation("gothaj/fonts/Roboto_Condensed-ExtraBold.ttf");

    public static final ResourceLocation karlaLightFont = new ResourceLocation("gothaj/fonts/Karla-Light.ttf");
    public static final ResourceLocation karlaRegularFont = new ResourceLocation("gothaj/fonts/Karla-Regular.ttf");
    public static final ResourceLocation karlaMediumFont = new ResourceLocation("gothaj/fonts/Karla-Medium.ttf");
    public static final ResourceLocation karlaSemiBoldFont = new ResourceLocation("gothaj/fonts/Karla-SemiBold.ttf");
    public static final ResourceLocation karlaBoldFont = new ResourceLocation("gothaj/fonts/Karla-Bold.ttf");

}
