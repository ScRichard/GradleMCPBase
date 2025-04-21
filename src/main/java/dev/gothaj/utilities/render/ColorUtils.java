package dev.gothaj.utilities.render;

import java.awt.*;

public class ColorUtils {

    private ColorUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Color mix(int colorInt1, int colorInt2, float transition) {
        Color color1 = new Color(colorInt1);
        Color color2 = new Color(colorInt2);

        double newR = Math.min(color2.getRed() + (color1.getRed() - color2.getRed()) * transition, 255);
        double newG = Math.min(color2.getGreen() +  (color1.getGreen() - color2.getGreen()) * transition, 255);
        double newB = Math.min(color2.getBlue() +  (color1.getBlue() - color2.getBlue()) * transition, 255);
        double newA = Math.min(color2.getAlpha() +  (color1.getAlpha() - color2.getAlpha()) * transition, 255);

        return new Color((int) newR,(int) newG,(int) newB,(int) newA);

    }

}
