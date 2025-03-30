package dev.gothaj.utilities.render;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class RenderUtils {


    // Main methods for rendering on screen
    public static void start2D() {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableTexture2D();
        GlStateManager.disableCull();
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
    }

    public static void stop2D() {
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.enableCull();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.resetColor();
    }

    public static void stop3D() {
        GlStateManager.enableCull();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void start3D() {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);

        GL11.glDepthMask(false);
        GlStateManager.disableCull();
    }
    // Color method easier than COLOR
    public static void color(int color) {
        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;

        GlStateManager.color(f, f1, f2, f3);
    }

    public static void drawRect(double x, double y, double width, double height, int color) {
        start2D(); // starting 2D rendering
        color(color); // setting up color of quads
        GL11.glBegin(GL11.GL_QUADS); // setting up drawing

        // Verteces
        {
            GL11.glVertex2d(x, y);
            GL11.glVertex2d(x + width, y);
            GL11.glVertex2d(x + width, y + height);
            GL11.glVertex2d(x, y + height);
        }

        GL11.glEnd(); // Stopping rendering

        stop2D(); //Stopping 2D rendering
    }

}
