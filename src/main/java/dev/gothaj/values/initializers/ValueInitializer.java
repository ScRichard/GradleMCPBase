package dev.gothaj.values.initializers;

public interface ValueInitializer {
    void execute();

    boolean canBeInitialized();
}
