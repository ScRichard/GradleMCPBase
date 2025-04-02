package dev.gothaj.features.modules;

import dev.gothaj.Client;
import dev.gothaj.features.modules.anotations.ModRegister;
import dev.gothaj.features.modules.initializers.ModInitializer;
import dev.gothaj.values.Value;
import lombok.*;
import net.minecraft.client.Minecraft;
import java.lang.reflect.Field;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mod implements ModInitializer {

    // Not as optimalized as is should be, but its better than setting for every class
    public static final Minecraft mc = Minecraft.getMinecraft();

    // Setting name, description, key and enabled
    private String name = this.getClass().getAnnotation(ModRegister.class).name();
    private String description = this.getClass().getAnnotation(ModRegister.class).description();
    private int key = this.getClass().getAnnotation(ModRegister.class).key();

    private boolean enabled;

    private final ArrayList<Value> settings = new ArrayList<>();

    @Override
    public void onEnable() {
        Client.INSTANCE.getEventBus().register(this);
    }

    @Override
    public void onDisable() {
        Client.INSTANCE.getEventBus().unregister(this);
    }

    @Override
    public void toggle() {
        this.enabled = !this.enabled;
        if (this.enabled) onEnable();
        else onDisable();
    }

    /*
    * Automatic setting registration
    */

    @Override
    public void registerSettings() {

        for(Field field : this.getClass().getDeclaredFields()) {
            if(!Value.class.isAssignableFrom(field.getType()))
                continue;

            try {
                Value value = (Value) field.get(field);
                if (value != null) {
                    settings.add(value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }
}
