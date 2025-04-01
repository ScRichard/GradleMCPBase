package dev.gothaj.features.modules;

import dev.gothaj.features.modules.initializers.ModuleManagerInitializer;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@Getter
@Setter
public class ModuleManager implements ModuleManagerInitializer {

    private String modulePackage = this.getClass().getPackage().getName() + ".mods";

    // Creating a module list
    @Singular
    private final ArrayList<Mod> modules = new ArrayList<>();

    @Override
    public void registerModules() {

        modules.clear();

        for(Class<?> clazz : new Reflections(modulePackage).getSubTypesOf(Mod.class)) {

            try {
                Mod mod = (Mod) clazz.getDeclaredConstructor().newInstance();

                modules.add(mod);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
