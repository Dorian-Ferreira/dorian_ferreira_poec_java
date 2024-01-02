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
public class PostalCode implements EntityInterface {

    private Long id;
    private City city;
    private String code;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\tCode Postal :  \n");

        result.append("\t\tID : ");
        result.append(id);
        result.append("\n");

        result.append("\t\tCode : ");
        result.append(code);
        result.append("\n");

        return result.toString();
    }
}