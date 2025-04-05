package dev.gothaj.values.impl;

import dev.gothaj.features.modules.Mod;
import dev.gothaj.values.Setting;
import dev.gothaj.values.Value;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class BooleanValue extends Value {

    private boolean enabled;

    public BooleanValue(String id, String displayName, boolean enabled, Supplier<Boolean> isVisible, Setting<?> setting, Mod parent) {
        super(id, displayName, isVisible, setting, parent);
        this.enabled = enabled;
    }
    public BooleanValue(String displayName, boolean enabled, Supplier<Boolean> isVisible, Setting<?> setting, Mod parent) {
        super(displayName, displayName, isVisible, setting, parent);
        this.enabled = enabled;
    }
    public BooleanValue(String id, String displayName, boolean enabled, Setting<?> setting, Mod parent) {
        super(id, displayName, () -> true, setting, parent);
        this.enabled = enabled;
    }
    public BooleanValue(String displayName, boolean enabled, Setting<?> setting, Mod parent) {
        super(displayName, displayName, () -> true, setting, parent);
        this.enabled = enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        this.execute();
    }

    @Override
    public void execute() {
        if(!this.canBeInitialized())
            return;

        if(this.enabled && this.getParent().isEnabled()) {
            this.getSetting().onEnable();
            return;
        }
        this.getSetting().onDisable();
    }
}
