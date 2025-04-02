package dev.gothaj.values;

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

    private Object parent;

    @Override
    public void execute() {
        if(setting == null)
            return;

        setting.execute();
    }
}
