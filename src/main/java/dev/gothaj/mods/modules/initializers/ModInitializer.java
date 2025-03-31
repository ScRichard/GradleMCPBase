package dev.gothaj.mods.modules.initializers;

public interface ModInitializer {
    void onEnable();
    void onDisable();

    void toggle();

}
