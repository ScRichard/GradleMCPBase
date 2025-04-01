package dev.gothaj.features.modules;

import dev.gothaj.Client;
import dev.gothaj.features.modules.anotations.ModRegister;
import dev.gothaj.features.modules.initializers.ModInitializer;
import lombok.*;
import net.minecraft.client.Minecraft;

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
}
