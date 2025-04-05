package dev.gothaj.values.impl;

import dev.gothaj.features.modules.Mod;
import dev.gothaj.values.Setting;
import dev.gothaj.values.Value;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;

import javax.management.ValueExp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

@Getter
public class ModeValue extends Value {

    private Value activeMode;
    private final ArrayList<Value> modes = new ArrayList<>();

    public ModeValue(String displayName, Value[] modes, Supplier<Boolean> isVisible, Mod parent) {
        super(displayName, displayName, isVisible, null, parent);
        this.modes.addAll(Arrays.asList(modes));
        activeMode = this.modes.get(0);
    }
    public ModeValue(String id, String displayName, Value[] modes,Supplier<Boolean> isVisible, Mod parent) {
        super(id, displayName, isVisible, null, parent);
        this.modes.addAll(Arrays.asList(modes));
        activeMode = this.modes.get(0);
    }
    public ModeValue(String displayName, Value[] modes, Mod parent) {
        super(displayName, displayName, () -> true, null, parent);
        this.modes.addAll(Arrays.asList(modes));
        activeMode = this.modes.get(0);
    }
    public ModeValue(String id, String displayName, Value[] modes, Mod parent) {
        super(id, displayName, () -> true, null, parent);
        this.modes.addAll(Arrays.asList(modes));
        activeMode = this.modes.get(0);
    }

    public void setActiveMode(Value mode) {
        if(activeMode != null && activeMode.getSetting() != null) {
            activeMode.getSetting().onDisable();
        }
        activeMode = mode;
        if(this.getParent().isEnabled() && activeMode.getSetting() != null) {
            activeMode.getSetting().onEnable();
        }
    }

    @Override
    public void execute() {
        if(!this.canBeInitialized())
            return;

        if(this.getParent().isEnabled()){
            this.getActiveMode().getSetting().onEnable();
            return;
        }
        this.getActiveMode().getSetting().onDisable();
    }

}
