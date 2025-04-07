package dev.gothaj.ui.clickgui.category;

import dev.gothaj.ui.clickgui.screens.UiScreen;
import dev.gothaj.utilities.render.RoundedUtils;
import dev.gothaj.utilities.ui.Position;
import dev.gothaj.utilities.ui.UiInitializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
public class CategoryButton implements UiInitializer {

    private String name;

    private final Position position = new Position(0, 0, 90, 25);

    private UiScreen uiScreen;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        RoundedUtils.drawRoundedRect(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 0x90000000, 3);

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {

    }

    @Override
    public void initGui() {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }
}
