package dev.gothaj.utilities.font.initializers;

public interface FontUtilsInitializer {
    float drawString(String text, double x, double y, int color);

    float getHeight();

    double getWidth(String text);
}
