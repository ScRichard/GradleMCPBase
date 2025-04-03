package dev.gothaj.values.initializers;

import dev.gothaj.features.modules.Mod;

public interface SettingInitializer {
    void onEnable();
    void onDisable();

    void execute();
}
