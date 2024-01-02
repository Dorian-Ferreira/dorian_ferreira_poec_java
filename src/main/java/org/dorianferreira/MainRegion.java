package org.dorianferreira;

import org.dorianferreira.entity.regions.City;
import org.dorianferreira.entity.regions.Region;
import org.dorianferreira.repository.DepartementRepository;
import org.dorianferreira.repository.RegionRepository;
import org.dorianferreira.service.Dump;

import java.util.HashMap;

public class MainRegion {
    final static private RegionRepository rr = RegionRepository.getRepository();

    public static void main(String[] args) {
        showAllRegions();
        showAllCitiesByRegion();
        showPopulationByRegion();
    }

    private static void showAllRegions() {
        System.out.println("Toutes les regions : ");
        for (Region r : rr.getAll()) {
            Dump.dump(r);
        }
    }

    private static void showPopulationByRegion() {
        Region population = new Region();
        population.setId(1L);

        System.out.println("Population pour la région avec l'id 1 : ");
        Dump.dump(rr.getOne(new HashMap<>(){{
            put("id", 1);
        }}));
        System.out.println(rr.findPopulationByRegion(population));
    }

    private static void showAllCitiesByRegion() {
        Region population = new Region();
        population.setId(1L);

        System.out.println("Toutes les villes de la région avec l'id 1 : ");
        Dump.dump(rr.getOne(new HashMap<>(){{
            put("id", 1);
        }}));

        for (City c : rr.findCitiesByRegion(population)) {
            System.out.println(c);
            Dump.dump(c.getDepartement());
            System.out.println();
        }
    }
}