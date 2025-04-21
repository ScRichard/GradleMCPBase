package dev.gothaj.ui.clickgui;

import dev.gothaj.features.modules.Category;
import dev.gothaj.ui.clickgui.category.CategoryButton;
import dev.gothaj.ui.clickgui.screens.UiScreen;
import dev.gothaj.utilities.client.Resources;
import dev.gothaj.utilities.font.rendering.FontRenderer;
import dev.gothaj.utilities.render.RoundedUtils;
import dev.gothaj.utilities.ui.Position;
import dev.gothaj.utilities.ui.UiInitializer;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.util.Tuple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
public class ClickGui implements UiInitializer {

    private Position position = new Position(0,0, 400, 300);

    private final TreeMap<String, ArrayList<CategoryButton>> buttons = new TreeMap<>();

    private FontRenderer font = new FontRenderer(Resources.karlaBoldFont, 30, new Tuple<Integer, Integer>(0, 128));

    public ClickGui() {

        this.createCategoryModuleButtons();


    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RoundedUtils.drawRoundedRect(position.getX(), position.getY(), position.getWidth(), position.getHeight(), 0x181818, 7);
        RoundedUtils.drawRoundedRectWithCorners(position.getX(), position.getY()+30, 100, position.getHeight()-30, 0x151515, new float[] { 0, 0, 0, 7 });

        RoundedUtils.drawRoundedRectWithCorners(position.getX(), position.getY(), position.getWidth(), 30, 0x121212, new float[] { 7, 7, 0, 0 });

        this.renderCategoryButtonsAt(position.getX() + 5, this.getPosition().getY() + 35, mouseX, mouseY, partialTicks);

        font.render("Nigga", 0,0, 0xffFFFFFF);

    }

    // Needed code review

    private void renderCategoryButtonsAt(double x, double y, int mouseX, int mouseY, float partialTicks) {
        double offset = 0;
        for (Map.Entry<String, ArrayList<CategoryButton>> category : this.buttons.entrySet()) {

            offset += 15;

            for (CategoryButton button : category.getValue()) {
                button.getPosition().setX(x);
                button.getPosition().setY(y + offset);

                button.drawScreen(mouseX, mouseY, partialTicks);

                offset += button.getPosition().getHeight() + 3;
            }
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
    }

    @Override
    public void initGui() {

    }
    // Creating a category buttons for modules
    private void createCategoryModuleButtons() {
        ArrayList<CategoryButton> modules = new ArrayList<>();
        for (Category category : Category.values()) {

            String name = category.name().toLowerCase();

            modules.add(new CategoryButton(name.substring(0, 1).toUpperCase() + name.substring(1), "test", new UiScreen()));
        }
        buttons.put("Modules", modules);



    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

}
