package dev.gothaj.features.modules.initializers;

public interface ModInitializer {
    void onEnable();
    void onDisable();

    void toggle();

}
