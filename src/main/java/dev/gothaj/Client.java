package dev.gothaj;

import dev.gothaj.mods.modules.ModuleManager;
import lombok.Getter;

@Getter
public enum Client {
    INSTANCE;

    private ModuleManager moduleManager;

    public void onEnable() {

        moduleManager = new ModuleManager();
    }
    public void onDisable() {

    }

}
