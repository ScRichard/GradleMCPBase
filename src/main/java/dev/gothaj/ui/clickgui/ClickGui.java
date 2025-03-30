package dev.gothaj.ui.clickgui;

import dev.gothaj.utilities.render.RenderUtils;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        RenderUtils.drawRect(0, 0, 100,100, 0x90000000);
    }
}
