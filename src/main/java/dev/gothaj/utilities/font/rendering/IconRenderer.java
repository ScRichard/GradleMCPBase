package dev.gothaj.utilities.font.rendering;

import dev.gothaj.utilities.font.AbstractFontRenderer;
import dev.gothaj.utilities.font.Glyph;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class IconRenderer extends AbstractFontRenderer<Character> {

    protected IconRenderer(ResourceLocation resourceLocation, int size, Character... characters) {
        super(resourceLocation, size, characters);

    }

    @Override
    public void generateTextures(Character... characters) {
        BufferedImage bufferedImage = new BufferedImage(this.getImageSize(), this.getImageSize(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = this.setupGraphics2D(bufferedImage);

        float positionX = 1;
        float positionY = 2;

        int ascent = graphics2D.getFontMetrics().getAscent();

        for (char chr : characters) {

            String str = Character.toString(chr);

            Rectangle2D boundingBox = this.getBoundsCache().computeIfAbsent(chr, c -> graphics2D.getFontMetrics().getStringBounds(str, graphics2D));

            Tuple<Float, Float> positions = this.createImage(str, positionX, positionY, graphics2D, ascent, boundingBox);

            positionX += positions.getFirst();
            positionY += positions.getSecond();
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

            if (glyph == null) continue;

            GL11.glBegin(GL11.GL_TRIANGLES);
            this.drawChar(character, (float) x, (float) y);
            GL11.glEnd();
            x += (glyph.getWidth() - 6.9F + this.getPadding() * this.getFontScaleOffset());

            ++i2;
        }

        stopTexture();
    }
}
