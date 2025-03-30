package dev.gothaj.mods.modules;

import dev.gothaj.mods.modules.anotations.ModRegister;
import dev.gothaj.mods.modules.initializers.ModInitializer;
import lombok.*;
import net.minecraft.client.Minecraft;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mod implements ModInitializer {

    // Not as optimalized as is should be, but its better than setting for every class
    public static Minecraft mc = Minecraft.getMinecraft();

    // Setting name, description, key and enabled
    private String name = this.getClass().getAnnotation(ModRegister.class).name(),
            description = this.getClass().getAnnotation(ModRegister.class).description();
    private int key = this.getClass().getAnnotation(ModRegister.class).key();

    private boolean enabled;

    @Override
    public void register() {

    }

    @Override
    public void unregister() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void toggle() {
        this.enabled = !this.enabled;
        if (this.enabled) onEnable();
        else onDisable();
    }
}
