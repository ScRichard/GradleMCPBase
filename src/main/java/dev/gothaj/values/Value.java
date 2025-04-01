package dev.gothaj.values;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter
@Setter
public class Value<T> {

    private T executor;

    private Object parent;

    private String id;
    private String displayName;

    private Supplier<Boolean> isVisible = () -> true;

}
