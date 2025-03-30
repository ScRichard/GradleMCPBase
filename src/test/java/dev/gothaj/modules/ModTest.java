package dev.gothaj.modules;

import dev.gothaj.mods.modules.Mod;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModTest {

    @Test
    public void createModule() {
        Mod mod = Mod.builder()
                .name("test")
                .description("test")
                .build();

        Assertions.assertThat(mod.getName()).isEqualTo("test");
        Assertions.assertThat(mod.getDescription()).isEqualTo("test");
    }
}
