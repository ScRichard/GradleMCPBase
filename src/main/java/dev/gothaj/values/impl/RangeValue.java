package dev.gothaj.values.impl;

import dev.gothaj.features.modules.Mod;
import dev.gothaj.values.Value;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter
@Setter
public class RangeValue extends Value {

    private double minValue;
    private double maxValue;
    private final double min;
    private final double max;
    private final double increment;

    public RangeValue(String id, String displayName, double minValue, double maxValue, double min, double max, double increment, Supplier<Boolean> isVisible, Mod parent) {
        super(id, displayName, isVisible, null, parent);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }
    public RangeValue(String displayName, double minValue, double maxValue, double min, double max, double increment, Supplier<Boolean> isVisible, Mod parent) {
        super(displayName, displayName, isVisible, null, parent);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }
    public RangeValue(String id, String displayName, double minValue, double maxValue, double min, double max, double increment, Mod parent) {
        super(id, displayName, () -> true, null, parent);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }
    public RangeValue(String displayName, double minValue, double maxValue, double min, double max, double increment, Mod parent) {
        super(displayName, displayName, () -> true, null, parent);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public void setMinValue(double value) {
        double precision = 1 / increment;
        this.minValue = Math.min(Math.round(Math.max(min, Math.min(max, value)) * precision) / precision, this.maxValue);

    }
    public void setMaxValue(double value) {
        double precision = 1 / increment;
        this.maxValue = Math.max(this.minValue ,Math.round(Math.max(min, Math.min(max, value)) * precision) / precision);
    }
}
