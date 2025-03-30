package dev.gothaj;

import dev.gothaj.mods.modules.ModuleManager;
import lombok.Getter;

@Getter
public enum Client {
    INSTANCE;

    private ModuleManager moduleManager;

    // Enabling a Client
    public void onEnable() {

        // Registring a module manager
        moduleManager = new ModuleManager();

    }

    // Disabling a client
    public void onDisable() {

    }

}
