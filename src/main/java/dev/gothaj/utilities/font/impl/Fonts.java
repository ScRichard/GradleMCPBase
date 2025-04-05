package dev.gothaj.utilities.font.impl;

import dev.gothaj.Client;
import dev.gothaj.utilities.font.FontConfig;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.text.WordUtils;
import org.lwjgl.opengl.GL11;

public class Fonts {

    private static final Minecraft mc = Minecraft.getMinecraft();

    private Fonts() {
        throw new IllegalStateException("Utility class");
    }

    public static void drawString(String text, double x, double y, int color, FontConfig fontType) {
        drawString(text, x, y, color, fontType, 1);
    }
    public static void drawString(String text, double x, double y, int color, FontConfig fontType, double scale) {
        GL11.glPushMatrix();
        GL11.glTranslated(x - x * scale, y - y * scale, 1);
        GL11.glScaled(scale, scale, 1);
        if(fontType.getValue().equalsIgnoreCase(FontConfig.MINECRAFT.getValue())) {
            mc.fontRendererObj.drawString(text, (int) x, (int) y, color);
        }else {
            Client.INSTANCE.getFontManager().getFont(fontType.getValue()).drawString(text, x, y, color);
        }
        GL11.glScaled(1, 1, 1);
        GL11.glPopMatrix();

    }

    public static double drawStringBoxed(String text, double x, double y, int color, int maxChars, FontConfig fontType, double scale) {
        GL11.glPushMatrix();
        GL11.glTranslated(x - x * scale, y - y * scale, 1);
        GL11.glScaled(scale, scale, 1);
        String[] wrappedText = WordUtils.wrap(text, (int) Math.round(maxChars/scale), null, false).split("\n");
        int i = 0;
        for(String t : wrappedText) {
            if(fontType.getValue().equalsIgnoreCase(FontConfig.MINECRAFT.getValue())) {
                mc.fontRendererObj.drawString(t, (int) x, (int) (y+i*getHeight(fontType, scale)), color);
            }else {
                Client.INSTANCE.getFontManager().getFont(fontType.getValue()).drawString(t, x, y+i*getHeight(fontType, scale), color);
            }
            i++;
        }
        GL11.glScaled(1, 1, 1);
        GL11.glPopMatrix();

        return (i*getHeight(fontType, scale));
    }

    public static void drawStringCentered(String text, double x, double y, int color, FontConfig fontType, double scale) {
        drawString(text,x-getWidth(text, fontType, scale)/2, y-getHeight(fontType, scale)/2, color, fontType, scale);
    }
    public static void drawStringCentered(String text, double x, double y, int color, FontConfig fontType) {
        drawString(text,x-getWidth(text, fontType)/2, y-getHeight(fontType)/2, color, fontType);
    }

    public static double getWidth(String text, FontConfig fontType, double scale) {
        if(fontType.getValue().equalsIgnoreCase(FontConfig.MINECRAFT.getValue())) {
            return mc.fontRendererObj.getStringWidth(text) * scale;
        }
        return Client.INSTANCE.getFontManager().getFont(fontType.getValue()).getWidth(text) * scale;
    }
    public static double getWidth(String text, FontConfig fontType) {
        return getWidth(text, fontType, 1);
    }
    public static double getHeight(FontConfig fontType, double scale) {
        if(fontType.getValue().equalsIgnoreCase(FontConfig.MINECRAFT.getValue())) {
            return mc.fontRendererObj.FONT_HEIGHT * scale;
        }

        return Client.INSTANCE.getFontManager().getFont(fontType.getValue()).getHeight() * scale;
    }
    public static double getHeight(FontConfig fontType) {
        return getHeight(fontType, 1);
    }

}
