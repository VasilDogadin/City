package org.javaacademy.registry.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum FamilyStatus {
    DIVORCED("Разведен"),
    MARRIED("В браке"),
    SINGLE("Не в браке");

    @Getter
    @NonNull
    String status;
}
