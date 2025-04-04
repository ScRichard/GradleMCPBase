package dev.gothaj.values.impl;

import dev.gothaj.features.modules.Mod;
import dev.gothaj.values.Setting;
import dev.gothaj.values.Value;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter
@Setter
public class NumberValue extends Value {

    private double value;
    private final double min;
    private final double max;
    private final double increment;

    public NumberValue(String id, String displayName, double value, double min, double max, double increment, Supplier<Boolean> isVisible, Mod parent) {
        super(id, displayName, isVisible, null, parent);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }
    public NumberValue(String displayName, double value, double min, double max, double increment, Supplier<Boolean> isVisible, Mod parent) {
        super(displayName, displayName, isVisible, null, parent);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }
    public NumberValue(String id, String displayName, double value, double min, double max, double increment, Mod parent) {
        super(id, displayName, () -> true, null, parent);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }
    public NumberValue(String displayName, double value, double min, double max, double increment, Mod parent) {
        super(displayName, displayName, () -> true, null, parent);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }
    public void setValue(double value) {
        double precision = 1 / increment;
        this.value = Math.min(Math.round(Math.max(min, Math.min(max, value)) * precision) / precision, this.max);

    }
}
