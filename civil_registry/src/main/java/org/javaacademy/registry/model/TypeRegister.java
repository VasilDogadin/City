package org.javaacademy.registry.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TypeRegister {
    BIRTH("Рождение"),
    WEDDINGS("Свадьба"),
    DIVORCE("Развод");

    @Getter
    @NonNull
    String value;
}
