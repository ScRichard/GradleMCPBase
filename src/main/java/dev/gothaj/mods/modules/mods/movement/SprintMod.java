package dev.gothaj.mods.modules.mods.movement;

import dev.gothaj.events.annotations.Subscribe;
import dev.gothaj.events.events.EventUpdate;
import dev.gothaj.mods.modules.Mod;
import dev.gothaj.mods.modules.anotations.ModRegister;
import org.lwjgl.input.Keyboard;

@ModRegister(name = "Heloo", key = Keyboard.KEY_R)
public class SprintMod extends Mod {


    @Subscribe
    public void onUpdate(EventUpdate e) {
        mc.thePlayer.sendChatMessage("Sprint Mod");
    }

}
