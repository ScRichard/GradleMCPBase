package dev.gothaj.utilities.font;

import dev.gothaj.utilities.font.initializers.FontUtilsInitializer;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FontUtils implements FontUtilsInitializer {

    private float imgSize = 512f;
    private CharData[] charData = new CharData[65535];
    private Font font;
    private boolean antiAlias;
    private boolean fractionalMetrics;
    private int fontHeight = -1;
    private int charOffset = 0;
    private DynamicTexture tex;

    public final double fontScaleOffset = 2;
    private final int[] colorCode = new int[32];

    // Constructor with font initialization
    public FontUtils(Font font) {
        this(font, true, true);
    }

    public FontUtils(Font font, boolean antiAlias, boolean fractionalMetrics) {
        this.font = font;
        this.antiAlias = antiAlias;
        this.fractionalMetrics = fractionalMetrics;
        this.tex = this.setupTexture(font, antiAlias, fractionalMetrics, this.charData);
        setupColorcodes();
    }

    // Setup texture for font rendering
    protected DynamicTexture setupTexture(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
        BufferedImage img = this.generateFontImage(font, antiAlias, fractionalMetrics, chars);
        try {
            return new DynamicTexture(img);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Setup color codes
    private void setupColorcodes() {
        int index = 0;
        while (index < 32) {
            int noClue = (index >> 3 & 1) * 85;
            int red = (index >> 2 & 1) * 170 + noClue;
            int green = (index >> 1 & 1) * 170 + noClue;
            int blue = (index >> 0 & 1) * 170 + noClue;

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

    // Generate the font image for character texture
    protected BufferedImage generateFontImage(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
        int localImageSize = (int) this.imgSize;
        BufferedImage bufferedImage = new BufferedImage(localImageSize, localImageSize, 2);
        Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
        g2.setFont(font);
        g2.setColor(new Color(255, 255, 255, 0));
        g2.fillRect(0, 0, localImageSize, localImageSize);
        g2.setColor(Color.WHITE);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON
                        : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                antiAlias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        FontMetrics fontMetrics = g2.getFontMetrics();
        int charHeight = 0;
        int positionX = 0;
        int positionY = 1;
        int i2 = 0;

        while (i2 < chars.length) {
            char ch2 = (char) i2;
            CharData character = new CharData();
            Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(ch2), g2);
            character.setWidth(dimensions.getBounds().width + 8);
            character.setHeight(dimensions.getBounds().height);

            if (positionX + character.getWidth() >= localImageSize) {
                positionX = 0;
                positionY += charHeight;
                charHeight = 0;
            }

            if (character.getHeight() > charHeight) {
                charHeight = character.getHeight();
            }

            character.setStoredX(positionX);
            character.setStoredY(positionY);


            if (character.getHeight() > this.fontHeight) {
                this.fontHeight = character.getHeight();
            }

            chars[i2] = character;
            g2.drawString(String.valueOf(ch2), positionX + 2, positionY + fontMetrics.getAscent());
            positionX += character.getWidth();
            ++i2;
        }

        return bufferedImage;
    }

    // Draw the string with support for Unicode characters
    public float drawString(String text, double x2, double y2, int color) {
        if (text == null) {
            return 0.0f;
        }

        CharData[] currentData = this.charData;
        float alpha = (color >> 24 & 255) / 255.0f;

        x2 *= 2.0 * fontScaleOffset;
        y2 = (y2) * 2.0 * fontScaleOffset;
        GL11.glPushMatrix();
        GlStateManager.scale(0.5 / fontScaleOffset, 0.5 / fontScaleOffset, 0.5 / fontScaleOffset);
        GL11.glEnable(GL11.GL_BLEND);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
        GlStateManager.color((color >> 16 & 255) / 255.0f, (color >> 8 & 255) / 255.0f,
                (color & 255) / 255.0f, alpha);
        int size = text.length();
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture(this.tex.getGlTextureId());
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.tex.getGlTextureId());
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

        int i2 = 0;
        while (i2 < size) {
            char character = text.charAt(i2);

            // Handle special formatting characters (like color codes, bold, italic, etc.)
            if (character == '\u00a7' && i2 < size) {
                int colorIndex = 21;

                try {
                    colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i2 + 1));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }

                if (colorIndex < 16) {
                    GlStateManager.bindTexture(this.tex.getGlTextureId());
                    currentData = this.charData;

                    int colorcode = this.colorCode[colorIndex];
                    GlStateManager.color( (colorcode >> 16 & 255) / 255.0f,
                            (colorcode >> 8 & 255) / 255.0f, (colorcode & 255) / 255.0f, alpha);
                }

                ++i2;
            } else if (character < currentData.length && character >= '\u0000') {
                GL11.glBegin(GL11.GL_TRIANGLES);
                this.drawChar(currentData, character, (float) x2, (float) y2);
                GL11.glEnd();
                x2 += (double) (currentData[character].getWidth() - 8.3f + this.charOffset);
            }

            ++i2;
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GL11.glPopMatrix();

        return (float) x2 / 2.0f;
    }

    public float getHeight() {
        return (float) ((this.fontHeight - (2 * fontScaleOffset)) / (2 * fontScaleOffset));
    }

    public double getWidth(String text) {
        int width = 0;
        char[] charArray = text.toCharArray();
        int length = charArray.length;
        int i2 = 0;

        while (i2 < length) {
            char c2 = charArray[i2];

            if (c2 < this.charData.length) {
                width += this.charData[c2].getWidth() - 8 + this.charOffset * this.fontScaleOffset;
            }

            ++i2;
        }

        return width / (2 * this.fontScaleOffset);
    }

    public void drawChar(CharData[] chars, char c2, float x2, float y2) throws ArrayIndexOutOfBoundsException {
        try {
            this.drawQuad(x2, y2, chars[c2].getWidth(), chars[c2].getHeight(), chars[c2].getStoredX(), chars[c2].getStoredY(),
                    chars[c2].getWidth(), chars[c2].getHeight());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected void drawQuad(float x2, float y2, float width, float height, float srcX, float srcY, float srcWidth,
                            float srcHeight) {
        float renderSRCX = srcX / this.imgSize;
        float renderSRCY = srcY / this.imgSize;
        float renderSRCWidth = srcWidth / this.imgSize;
        float renderSRCHeight = srcHeight / this.imgSize;

        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d(x2 + width, y2);
        GL11.glTexCoord2f(renderSRCX, renderSRCY);
        GL11.glVertex2d(x2, y2);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x2, y2 + height);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x2, y2 + height);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x2 + width, y2 + height);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d(x2 + width, y2);
    }
}