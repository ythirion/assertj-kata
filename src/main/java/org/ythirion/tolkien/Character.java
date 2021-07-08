package org.ythirion.tolkien;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Character {
    private final String name;
    private final int age;
    private final Race race;
}