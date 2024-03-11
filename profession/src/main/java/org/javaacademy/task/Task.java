package org.javaacademy.task;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Task {
    final String description;
    boolean isDone;
    double timeConsumed;
}
