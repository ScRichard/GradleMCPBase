package dev.gothaj.features.modules.mods.movement;

import dev.gothaj.events.annotations.Subscribe;
import dev.gothaj.events.events.EventUpdate;
import dev.gothaj.features.modules.Mod;
import dev.gothaj.features.modules.anotations.ModRegister;
import dev.gothaj.values.impl.BooleanValue;
import org.lwjgl.input.Keyboard;

@ModRegister(name="Speed", description = "Allows you to run faster", key = Keyboard.KEY_X)
public class SpeedMod extends Mod {

    @Subscribe
    public void onUpdate(EventUpdate e) {
        mc.thePlayer.motionX *= 2;
        mc.thePlayer.motionZ *= 2;
    }

}
