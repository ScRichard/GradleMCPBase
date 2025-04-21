package dev.gothaj.utilities.ui;

import java.io.IOException;

public interface UiInitializer {

    void drawScreen(int mouseX, int mouseY, float partialTicks);
    void keyTyped(char typedChar, int keyCode) throws IOException;
    void initGui();
    void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException;
    void mouseReleased(int mouseX, int mouseY, int state);

}
