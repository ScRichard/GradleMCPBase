package dev.gothaj.mods.modules;

import dev.gothaj.mods.modules.initializers.ModuleManagerInitializer;
import dev.gothaj.mods.modules.mods.client.ClickGuiMod;
import dev.gothaj.mods.modules.mods.movement.SprintMod;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class ModuleManager implements ModuleManagerInitializer {

    // Creating a module list
    private final ArrayList<Mod> modules = new ArrayList<>();

    public ModuleManager() {
        registerModules();
    }

    @Override
    public void registerModules() {
        modules.add(new ClickGuiMod());
        modules.add(new SprintMod());
    }

    @Override
    public void addModule() {

    }

    @Override
    public void removeModule() {

    }
}
