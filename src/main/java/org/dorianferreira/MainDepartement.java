package org.dorianferreira;

import org.dorianferreira.entity.regions.City;
import org.dorianferreira.entity.regions.Departement;
import org.dorianferreira.entity.regions.Region;
import org.dorianferreira.repository.DepartementRepository;
import org.dorianferreira.repository.RegionRepository;
import org.dorianferreira.service.Dump;

import java.util.HashMap;
import java.util.List;

public class MainDepartement {
    final static private DepartementRepository dr = DepartementRepository.getRepository();

    public static void main(String[] args) {
//        showAllDepartments();
//        showAllDepartmentFromRegion();
        showAllPopulationFromDepartment();
    }

    private static void showAllDepartments() {
        System.out.println("Tout les départements : ");
        for (Departement d : dr.getAll()) {
            Dump.dump(d);
        }
    }

    private static void showAllDepartmentFromRegion() {
        Region toFind = new Region();
        toFind.setId(1L);

        System.out.println("Tout les départements de la région avec l'id 1 : ");
        for (Departement d : dr.findDepartmentsByRegion(toFind)) {
            Dump.dump(d);
        }
    }

    private static void showAllPopulationFromDepartment() {
        Departement population = new Departement();
        population.setId(1L);

        System.out.println("Population pour le département avec l'id 1 : ");
        Dump.dump(dr.getOne(new HashMap<>(){{
            put("id", population.getId());
        }}));
        System.out.println(dr.findPopulationByDepartement(population));
    }
}