package dev.gothaj.modules;

import dev.gothaj.features.modules.ModuleManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModuleManagerTest {

    protected ModuleManager moduleManager;

    @BeforeEach
    void init() {
        moduleManager = new ModuleManager();
        moduleManager.setModulePackage(this.getClass().getPackage().getName() + ".mods");
    }
    @Test
    void automaticRegistartionTest() {

        Assertions.assertEquals(0, moduleManager.getModules().size());

        moduleManager.registerModules();

        Assertions.assertEquals(1, moduleManager.getModules().size());

    }

}
