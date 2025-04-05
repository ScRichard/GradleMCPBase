package dev.gothaj.utilities.font;

import lombok.Getter;

@Getter
public enum FontConfig {
    MINECRAFT("minecraft"),
    ROBOTO("roboto");

    private final String value;

    FontConfig(String value) {
        this.value = value;
    }
}
