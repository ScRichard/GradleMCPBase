package dev.gothaj.features.modules.mods.client;

import dev.gothaj.features.modules.Mod;
import dev.gothaj.features.modules.anotations.ModRegister;
import dev.gothaj.ui.clickgui.ClickGui;
import org.lwjgl.input.Keyboard;

@ModRegister(name = "Gui", description = "Opens menu for hack client", key = Keyboard.KEY_RSHIFT)
public class ClickGuiMod extends Mod {

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new ClickGui());
        this.toggle();
    }
}
