package dev.gothaj.ui;

import dev.gothaj.ui.clickgui.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.io.IOException;

public class RenderingScreen extends GuiScreen {

    private final ClickGui clickGui = new ClickGui();

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        clickGui.getPosition().setX(sr.getScaledWidth() /2D - clickGui.getPosition().getWidth() / 2D);
        clickGui.getPosition().setY(sr.getScaledHeight() /2D - clickGui.getPosition().getHeight() / 2D);

        clickGui.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        clickGui.keyTyped(typedChar, keyCode);
    }

    @Override
    public void initGui() {
        super.initGui();

        clickGui.initGui();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        clickGui.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        clickGui.mouseReleased(mouseX, mouseY, state);
    }
}
