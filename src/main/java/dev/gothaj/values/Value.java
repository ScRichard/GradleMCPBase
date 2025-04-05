package dev.gothaj.values;

import dev.gothaj.features.modules.Mod;
import dev.gothaj.values.initializers.ValueInitializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter
@Setter
@AllArgsConstructor
public class Value implements ValueInitializer {

    private String id;
    private String displayName;

    private Supplier<Boolean> isVisible = () -> true;

    private Setting<?> setting;

    private Mod parent;

    public Value(String displayName, Setting<?> setting, Mod parent) {
        this.displayName = displayName;
        this.setting = setting;
        this.parent = parent;
        this.id = displayName;
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean canBeInitialized() {
        return setting == null;
    }

}
