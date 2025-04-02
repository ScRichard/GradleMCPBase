package dev.gothaj.values.impl;

import dev.gothaj.values.Setting;
import dev.gothaj.values.Value;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter
@Setter
public class BooleanValue extends Value {

    private boolean enabled;

    public BooleanValue(String id, String displayName, boolean enabled, Supplier<Boolean> isVisible, Setting<?> setting, Object parent) {
        super(id, displayName, isVisible, setting, parent);
        this.enabled = enabled;
    }
    public BooleanValue(String displayName, boolean enabled, Supplier<Boolean> isVisible, Setting<?> setting, Object parent) {
        super(displayName, displayName, isVisible, setting, parent);
        this.enabled = enabled;
    }
    public BooleanValue(String id, String displayName, boolean enabled, Setting<?> setting, Object parent) {
        super(id, displayName, () -> true, setting, parent);
        this.enabled = enabled;
    }
    public BooleanValue(String displayName, boolean enabled, Setting<?> setting, Object parent) {
        super(displayName, displayName, () -> true, setting, parent);
        this.enabled = enabled;
    }
}
