package dev.gothaj.utilities.font.rendering;

import dev.gothaj.utilities.font.AbstractFontRenderer;
import dev.gothaj.utilities.font.Glyph;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class FontRenderer extends AbstractFontRenderer<Tuple<Integer, Integer>> {

    private final int[] colorCode = new int[32];

    @SafeVarargs
    public FontRenderer(ResourceLocation resourceLocation, int size, Tuple<Integer, Integer>... characters) {
        super(resourceLocation, size, characters);

        this.setupColorcodes();
    }

    @SafeVarargs
    @Override
    public final void generateTextures(Tuple<Integer, Integer>... characters) {
        BufferedImage bufferedImage = new BufferedImage(this.getImageSize(), this.getImageSize(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = this.setupGraphics2D(bufferedImage);

        FontMetrics fontMetrics = graphics2D.getFontMetrics();

        float positionX = 1;
        float positionY = 2;
        int ascent = fontMetrics.getAscent();

        for (Tuple<Integer, Integer> range : characters) {
            for (int i = range.getFirst(); i <= range.getSecond(); i++) {
                char chr = (char) i;

                String str = Character.toString(chr);
                Rectangle2D boundingBox = this.getBoundsCache().computeIfAbsent(chr, c -> graphics2D.getFontMetrics().getStringBounds(str, graphics2D));

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
                this.getGlyphs().put(chr, glyph);

                positionX += (float) (width + 4);

            }
        }
        this.setTexture(new DynamicTexture(bufferedImage));

        this.getBoundsCache().clear();
    }

    @Override
    public void render(String text, double x, double y, int color) {
        float alpha = (color >> 24 & 255) / 255.0f;

        x *= 2.0 * this.getFontScaleOffset();
        y = (y) * 2.0 * this.getFontScaleOffset();

        startTexture(color, alpha);

        int i2 = 0;
        while (i2 < text.length()) {
            char character = text.charAt(i2);
            Glyph glyph = this.getGlyphs().get(character);
            // Handle special formatting characters (like color codes, bold, italic, etc.)
            if (character == '\u00a7') {
                int colorIndex = 21;

                try {
                    colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i2 + 1));
                } catch (Exception ignored) {} // Noncompliant: is the block empty on purpose, or is code missing?

                if (colorIndex < 16) {
                    GlStateManager.bindTexture(this.getTexture().getGlTextureId());

                    int colorcode = this.colorCode[colorIndex];
                    GlStateManager.color( (colorcode >> 16 & 255) / 255.0f,
                            (colorcode >> 8 & 255) / 255.0f, (colorcode & 255) / 255.0f, alpha);
                }

                ++i2;
            } else if (glyph != null) {
                GL11.glBegin(GL11.GL_TRIANGLES);
                this.drawChar(character, (float) x, (float) y);
                GL11.glEnd();
                x += (this.getGlyphs().get(character).getWidth() - 6.9F + this.getPadding() * this.getFontScaleOffset());
            }

            ++i2;
        }

        stopTexture();
    }
    private void setupColorcodes() {
        int index = 0;
        while (index < 32) {
            int noClue = (index >> 3 & 1) * 85;
            int red = (index >> 2 & 1) * 170 + noClue;
            int green = (index >> 1 & 1) * 170 + noClue;
            int blue = (index & 1) * 170 + noClue;

            if (index == 6) {
                red += 85;
            }

            if (index >= 16) {
                red /= 4;
                green /= 4;
                blue /= 4;
            }

            this.colorCode[index] = (red & 255) << 16 | (green & 255) << 8 | blue & 255;
            ++index;
        }
    }
}
