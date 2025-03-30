package dev.gothaj.mods.modules;

import dev.gothaj.mods.modules.initializers.ModInitializer;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mod implements ModInitializer {

    private String name, description;

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
}
