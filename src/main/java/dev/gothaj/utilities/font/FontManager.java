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

        fonts.put("roboto", new FontUtils(getResource(Resources.robotoFont, 30)));
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
