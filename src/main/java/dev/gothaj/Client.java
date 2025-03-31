package dev.gothaj;

import dev.gothaj.events.EventBus;
import dev.gothaj.mods.modules.ModuleManager;
import lombok.Getter;

@Getter
public enum Client {
    INSTANCE;

    private ModuleManager moduleManager;
    private EventBus eventBus;

    // Enabling a Client
    public void onEnable() {

        // Registring a module manager
        moduleManager = new ModuleManager();
        eventBus = new EventBus();
        eventBus.registerPriorities();

    }

    // Disabling a client
    public void onDisable() {

    }

}
