package dev.gothaj.mods.modules.mods.movement;

import dev.gothaj.events.annotations.Subscribe;
import dev.gothaj.events.events.EventUpdate;
import dev.gothaj.mods.modules.Mod;
import dev.gothaj.mods.modules.anotations.ModRegister;
import org.lwjgl.input.Keyboard;

@ModRegister(name = "Sprint", description = "Allows you to sprint", key = Keyboard.KEY_R)
public class SprintMod extends Mod {
    
    @Subscribe
    public void onUpdate(EventUpdate e) {
        mc.thePlayer.setSprinting(true);
    }

}
