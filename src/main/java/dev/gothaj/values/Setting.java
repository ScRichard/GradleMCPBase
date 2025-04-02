package dev.gothaj.values;

import dev.gothaj.Client;
import dev.gothaj.features.modules.Mod;
import dev.gothaj.values.initializers.SettingInitializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.client.Minecraft;

@Getter
@Setter
@RequiredArgsConstructor
public class Setting<T> implements SettingInitializer {

    public static final Minecraft mc = Minecraft.getMinecraft();

    private final T parent;

    private boolean running;

    @Override
    public void onEnable() {
        Client.INSTANCE.getEventBus().register(this);
        running = true;
    }

    @Override
    public void onDisable() {
        Client.INSTANCE.getEventBus().unregister(this);
        running = false;
    }

    @Override
    public void execute() {

        if(running) {
            this.onDisable();
            return;
        }
        this.onEnable();
    }


}
