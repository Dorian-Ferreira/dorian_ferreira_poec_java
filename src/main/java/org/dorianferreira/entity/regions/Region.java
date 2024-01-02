package org.dorianferreira.entity.regions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dorianferreira.entity.EntityInterface;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Region implements EntityInterface {

    private Long id;
    private String name;
    private String code;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}