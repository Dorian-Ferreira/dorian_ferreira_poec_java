package org.dorianferreira;

import org.dorianferreira.entity.regions.City;
import org.dorianferreira.entity.regions.Departement;
import org.dorianferreira.entity.regions.PostalCode;
import org.dorianferreira.entity.regions.Region;
import org.dorianferreira.repository.CityRepository;
import org.dorianferreira.service.Dump;

import java.util.List;

public class MainCity {
    final static private CityRepository cr = CityRepository.getRepository();

    public static void main(String[] args) {
        showAllCities();
        showAllCitiesOfDepartment();
    }

    private static void showAllCities() {
        System.out.println("Toutes les villes : ");
        for (City c : cr.getAll()) {
            System.out.println(c);
            Dump.dump(c.getDepartement());
            System.out.println();
        }
    }

    private static void showAllCitiesOfDepartment() {
        Departement toFind = new Departement();
        toFind.setId(1L);

        System.out.println("Tout les viles du département avec l'id 1 : ");
        List<City> cities = cr.findCityByDepartement(toFind);

        Dump.dump(cities.get(0).getDepartement());
        for (City c : cities) {
            System.out.println(c);
        }
    }
}