package dev.gothaj.mods.modules;

import dev.gothaj.mods.modules.initializers.ModuleManagerInitializer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ModuleManager implements ModuleManagerInitializer {

    // Creating a module list
    private final ArrayList<Mod> modules = new ArrayList<>();

    public ModuleManager() {
        registerModules();
    }

    @Override
    public void registerModules() {

    }

    @Override
    public void addModule() {

    }

    @Override
    public void removeModule() {

    }
}
