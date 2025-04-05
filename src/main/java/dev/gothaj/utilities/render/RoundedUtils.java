package dev.gothaj.utilities.render;

import dev.gothaj.utilities.client.Resources;
import dev.gothaj.utilities.shader.Shader;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RoundedUtils {

    private RoundedUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Shader roundedRectShader = new Shader(Resources.roundedRectShader);

    public static void drawRoundedRect(int x, int y, int width, int height, int color, float rounding) {
        drawRoundedRectWithCorners(x,y,width,height, color, new float[] {
                rounding,rounding,rounding,rounding
        });
    }

    public static void drawRoundedRectWithCorners(int x, int y, int width, int height, int color, float[] corners) {

        Color c = new Color(color);

        GlStateManager.enableBlend();
        roundedRectShader.startProgram();

        roundedRectShader.uniform4f("radius", corners[3], corners[0],corners[2], corners[1]);
        roundedRectShader.uniform2f("size", width, height);
        roundedRectShader.uniform4f("color", c.getRed() /255F, c.getAlpha() /255F, c.getBlue() /255F, c.getAlpha() /255F);

        int textureID = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

        roundedRectShader.renderShader(x, y, width, height);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

        roundedRectShader.stopProgram();
        GlStateManager.disableBlend();
    }

}
