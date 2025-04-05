package dev.gothaj.values.impl;

import dev.gothaj.features.modules.Mod;
import dev.gothaj.values.Setting;
import dev.gothaj.values.Value;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter
@Setter
public class LocationValue extends Value {

    private double x;
    private double y;


    public LocationValue(String id, String displayName, double x, double y, Supplier<Boolean> isVisible, Setting<?> setting, Mod parent) {
        super(id, displayName, isVisible, setting, parent);

        this.x = x;
        this.y = y;
    }
    public LocationValue(String displayName, double x, double y, Supplier<Boolean> isVisible, Setting<?> setting, Mod parent) {
        super(displayName, displayName, isVisible, setting, parent);

        this.x = x;
        this.y = y;
    }
    public LocationValue(String id, String displayName, double x, double y, Setting<?> setting, Mod parent) {
        super(id, displayName, () -> true, setting, parent);

        this.x = x;
        this.y = y;
    }
    public LocationValue(String displayName, double x, double y, Setting<?> setting, Mod parent) {
        super(displayName, displayName, () -> true, setting, parent);

        this.x = x;
        this.y = y;
    }
}
