package org.dorianferreira.entity.regions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dorianferreira.entity.EntityInterface;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City implements EntityInterface {

    private Long id;
    private Departement departement;
    private String siren;
    private int population;
    private String name;
    private String code;
    public List<PostalCode> postalCodes = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void addPostalCode(PostalCode pc) {
        if(!postalCodes.contains(pc)) {
            postalCodes.add(pc);
            pc.setCity(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("City : \n");
        result.append("\tID : ");
        result.append(id);
        result.append("\n");

        result.append("\tNom : ");
        result.append(name);
        result.append("\n");

        result.append("\tCode : ");
        result.append(code);
        result.append("\n");

        result.append("\tPopulation : ");
        result.append(population).append("\n");

        result.append("\tSIREN : ");
        result.append(siren);
        result.append("\n");

        for (PostalCode pc : postalCodes) {
            result.append(pc.toString());
        }
        return result.toString();
    }
}