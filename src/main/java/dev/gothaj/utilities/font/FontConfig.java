package dev.gothaj.utilities.font;

import lombok.Getter;

@Getter
public enum FontConfig {
    MINECRAFT("minecraft"),
    ROBOTO("roboto"),
    ROBOTO_BOLD("roboto_bold"),
    ROBOTO_MEDIUM("roboto_medium"),
    ROBOTO_EXTRABOLD("roboto_extra_bold"),
    KARLA_LIGHT("karla_light"),
    KARLA_REGULAR("karla_regular"),
    KARLA_MEDIUM("karla_medium"),
    KARLA_SEMIBOLD("karla_semibold"),
    KARLA_BOLD("karla_bold"),
    ;


    private final String value;

    FontConfig(String value) {
        this.value = value;
    }
}
