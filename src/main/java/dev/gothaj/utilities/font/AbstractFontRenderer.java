package dev.gothaj.utilities.font;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract base class for bitmap font rendering in Minecraft.
 * Responsible for loading fonts, generating glyph textures, and rendering.
 *
 * @param <T> Type representing character ranges or parameters for texture generation.
 *
 */
@Getter
@Setter
public abstract class AbstractFontRenderer<T> {

    private Font font;                            // The font used for glyph generation
    private int size;                             // Font size
    private int imageSize = 512;                  // Texture atlas size
    private DynamicTexture texture;               // Minecraft dynamic texture for rendered characters
    private final TreeMap<Character, Glyph> glyphs = new TreeMap<>();

    private final Map<Character, Rectangle2D> boundsCache = new HashMap<>();

    private float fontHeight;                     // Maximum character height
    private int padding = 5;                      // Spacing between characters
    private float fontScaleOffset = 1.0F;         // Font scaling (zoom)

    @SafeVarargs
    protected AbstractFontRenderer(ResourceLocation resourceLocation, int size, T... characters) {
        this.size = size;
        try {
            this.setFont(this.load(resourceLocation));
        } catch (IOException | FontFormatException e) {
            this.setFont(new Font("Arial", Font.PLAIN, size));  // Fallback if font fails to load
        }

        this.generateTextures(characters); // Generate glyphs and texture
    }

    /**
     * Loads a font from a Minecraft ResourceLocation.
     */
    public Font load(ResourceLocation resourceLocation) throws IOException, FontFormatException {
        InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream();
        return Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(Font.PLAIN, size);
    }

    /**
     * Abstract method to generate glyphs and textures.
     */
    public abstract void generateTextures(T... characters);

    /**
     * Initializes a Graphics2D object for drawing characters.
     */
    public Graphics2D setupGraphics2D(BufferedImage bufferedImage) {
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setFont(this.font);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return graphics2D;
    }

    /**
     * Creates and stores a glyph image for a single character.
     */
    public Tuple<Float, Float> createImage(String str, float positionX, float positionY, Graphics2D graphics2D, int ascent, Rectangle2D boundingBox) {

        Glyph glyph = new Glyph();

        double width = boundingBox.getWidth() + 1;
        double height = boundingBox.getHeight() + 2;

        glyph.setWidth(width);
        glyph.setHeigth(height);

        if (this.getFontHeight() < height) {
            this.setFontHeight((float) height);
        }

        if (positionX + width > this.getImageSize()) {
            positionX = 1;
            positionY += 2 + this.getFontHeight();
        }

        glyph.setX(positionX);
        glyph.setY(positionY);

        graphics2D.drawString(str, (int) positionX, (int) (positionY + ascent));
        this.getGlyphs().put(str.toCharArray()[0], glyph);

        positionX += (float) (width + 4);
        return new Tuple<>(positionX, positionY);
    }

    /**
     * Abstract method to render text.
     */
    public abstract void render(String text, double x, double y, int color);

    /**
     * Calculates the width of a string using glyph widths.
     */
    public double getWidth(String text) {
        double width = 0;
        for (char chr : text.toCharArray()) {
            Glyph glyph = this.getGlyphs().get(chr);
            if (glyph == null) continue;
            width += glyph.getWidth() - 6.9F + this.getPadding() * this.getFontScaleOffset();
        }
        return width;
    }

    /**
     * Sets up OpenGL state for rendering the font texture.
     */
    public void startTexture(int color, float alpha) {
        GL11.glPushMatrix();
        GlStateManager.scale(0.5 / fontScaleOffset, 0.5 / fontScaleOffset, 0.5 / fontScaleOffset);
        GL11.glEnable(GL11.GL_BLEND);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
        GlStateManager.color((color >> 16 & 255) / 255.0f, (color >> 8 & 255) / 255.0f,
                (color & 255) / 255.0f, alpha);
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture(this.texture.getGlTextureId());
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texture.getGlTextureId());
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
    }

    /**
     * Restores OpenGL state after text rendering.
     */
    public void stopTexture() {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GL11.glPopMatrix();
    }

    /**
     * Draws a single character using pre-rendered glyphs.
     */
    public void drawChar(char chr, double x, double y) {
        Glyph glyph = this.getGlyphs().get(chr);
        if (glyph == null) return;

        float renderSRCX = (float) (glyph.getX() / this.getImageSize());
        float renderSRCY = (float) (glyph.getY() / this.getImageSize());
        float renderSRCWidth = (float) (glyph.getWidth() / this.getImageSize());
        float renderSRCHeight = (float) (glyph.getHeigth() / this.getImageSize());

        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d(x + glyph.getWidth(), y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY);
        GL11.glVertex2d(x, y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x, y + glyph.getHeigth());
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x, y + glyph.getHeigth());
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x + glyph.getWidth(), y + glyph.getHeigth());
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d(x + glyph.getWidth(), y);
    }

}

