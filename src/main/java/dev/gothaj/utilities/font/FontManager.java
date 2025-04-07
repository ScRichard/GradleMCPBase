package dev.gothaj.utilities.font;

import dev.gothaj.utilities.client.Resources;
import dev.gothaj.utilities.font.initializers.FontManagerInitializer;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class FontManager implements FontManagerInitializer {

    private final HashMap<String, FontUtils> fonts = new HashMap<>();

    public FontManager() {

        fonts.put(FontConfig.ROBOTO.getValue(), new FontUtils(getResource(Resources.robotoFont, 30)));
        fonts.put(FontConfig.ROBOTO_MEDIUM.getValue(), new FontUtils(getResource(Resources.robotoMediumFont, 30)));
        fonts.put(FontConfig.ROBOTO_BOLD.getValue(), new FontUtils(getResource(Resources.robotoBoldFont, 30)));
        fonts.put(FontConfig.ROBOTO_EXTRABOLD.getValue(), new FontUtils(getResource(Resources.robotoExtraBoldFont, 30)));

        fonts.put(FontConfig.KARLA_LIGHT.getValue(), new FontUtils(getResource(Resources.karlaLightFont, 30)));
        fonts.put(FontConfig.KARLA_REGULAR.getValue(), new FontUtils(getResource(Resources.karlaRegularFont, 30)));
        fonts.put(FontConfig.KARLA_MEDIUM.getValue(), new FontUtils(getResource(Resources.karlaMediumFont, 30)));
        fonts.put(FontConfig.KARLA_SEMIBOLD.getValue(), new FontUtils(getResource(Resources.karlaSemiBoldFont, 30)));
        fonts.put(FontConfig.KARLA_BOLD.getValue(), new FontUtils(getResource(Resources.karlaBoldFont, 30)));
    }

    public FontUtils getFont(String font) {
        for(Map.Entry<String, FontUtils> entry : fonts.entrySet()) {
            if(entry.getKey().equalsIgnoreCase(font.toLowerCase())) return entry.getValue();
        }
        return null;
    }

    public static String getLocation(String name) {
        return "gothaj/fonts/"+ name+".ttf";
    }


    @Override
    public Font getResource(ResourceLocation fontFile, float size) {

        InputStream is = null;
        try {
            is = Minecraft.getMinecraft().getResourceManager().getResource(fontFile).getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return Font.createFont(0, is).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
