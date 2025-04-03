package dev.gothaj.features.modules.mods.movement.speeds;

import dev.gothaj.events.annotations.Subscribe;
import dev.gothaj.events.events.EventUpdate;
import dev.gothaj.features.modules.mods.movement.SpeedMod;
import dev.gothaj.values.Setting;

public class BypassSetting extends Setting<SpeedMod> {

    public BypassSetting(SpeedMod parent) {
        super(parent);
    }

    @Subscribe
    public void onUpdate(EventUpdate event) {
        if(mc.thePlayer.onGround)
            mc.thePlayer.jump();
    }

}
