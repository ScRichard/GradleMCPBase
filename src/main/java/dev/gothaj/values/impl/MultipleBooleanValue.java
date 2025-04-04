package dev.gothaj.values.impl;

import dev.gothaj.features.modules.Mod;
import dev.gothaj.values.Value;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.util.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Getter
@Setter
public class MultipleBooleanValue extends Value {

    private ArrayList<Tuple<String, Boolean>> values = new ArrayList<>();

    public MultipleBooleanValue(String id, String displayName, Tuple<String, Boolean>[] values, Supplier<Boolean> isVisible, Mod parent) {
        super(id, displayName, isVisible, null, parent);
        this.values.addAll(Arrays.asList(values));
    }
    public MultipleBooleanValue(String displayName, Tuple<String, Boolean>[] values, Supplier<Boolean> isVisible, Mod parent) {
        super(displayName, displayName, isVisible, null, parent);
        this.values.addAll(Arrays.asList(values));
    }
    public MultipleBooleanValue(String id, String displayName, Tuple<String, Boolean>[] values, Mod parent) {
        super(id, displayName, () -> true, null, parent);
        this.values.addAll(Arrays.asList(values));
    }
    public MultipleBooleanValue(String displayName, Tuple<String, Boolean>[] values, Mod parent) {
        super(displayName, displayName, () -> true, null, parent);
        this.values.addAll(Arrays.asList(values));
    }
    public boolean get(String name) {
        Tuple<String, Boolean> value = values.stream().filter(v -> v.getFirst().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (value == null) return false;

        return value.getSecond();
    }

    public List<Tuple<String, Boolean>> getEnabledValues() {
        return values.stream().filter(Tuple::getSecond).collect(Collectors.toList());
    }

}
