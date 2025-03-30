package dev.gothaj.mods.modules.initializers;

public interface ModInitializer {

    void register();
    void unregister();

    void onEnable();
    void onDisable();

}
