package org.dorianferreira.entity.digimon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Digimon {
    private String name;
    private String image;
    private String level;
}