package dev.gothaj.features.modules.mods.movement;

import dev.gothaj.events.annotations.Subscribe;
import dev.gothaj.events.events.EventUpdate;
import dev.gothaj.features.modules.Mod;
import dev.gothaj.features.modules.anotations.ModRegister;
import dev.gothaj.features.modules.mods.movement.speeds.BypassSetting;
import dev.gothaj.values.Value;
import dev.gothaj.values.impl.ModeValue;
import org.lwjgl.input.Keyboard;

@ModRegister(name="Speed", description = "Allows you to run faster", key = Keyboard.KEY_X)
public class SpeedMod extends Mod {

    public final ModeValue modes = new ModeValue("Mode", new Value[] {
            new Value("Bypass", new BypassSetting(this), this)
    }, this);

    @Subscribe
    public void onUpdate(EventUpdate e) {

        mc.thePlayer.sendChatMessage(this.getSettings() + "");

        if(mc.thePlayer.moveForward > 0 || mc.thePlayer.moveStrafing >0){
            mc.thePlayer.motionX *= 2;
            mc.thePlayer.motionZ *= 2;
        }
    }

}
