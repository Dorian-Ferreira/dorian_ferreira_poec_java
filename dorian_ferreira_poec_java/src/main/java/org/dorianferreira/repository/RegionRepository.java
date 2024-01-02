package org.dorianferreira.repository;

import org.dorianferreira.entity.regions.City;
import org.dorianferreira.entity.regions.Departement;
import org.dorianferreira.entity.regions.Region;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegionRepository extends AbstractRepository<Region> {

    private static RegionRepository instance;
    private static final DepartementRepository dr = DepartementRepository.getRepository();
    private static final CityRepository cr = CityRepository.getRepository();

    private RegionRepository() {
        setTable("region");
    }

    public static RegionRepository getRepository() {
        if(instance == null) {
            instance = new RegionRepository();
        }
        return instance;
    }

    public int findPopulationByRegion(Region region) {
        return dr.findDepartmentsByRegion(region).stream().mapToInt(dr::findPopulationByDepartement).sum();
    }

    public List<City> findCitiesByRegion(Region region) {
        List<City> cities = new ArrayList<>();
        for (Departement d : dr.findDepartmentsByRegion(region)) {
            cities.addAll(cr.findCityByDepartement(d));
        }
        return cities;
    }

    @Override
    protected Region insert(Region toSave) {
        return null;
    }

    @Override
    protected Region update(Region toSave) {
        return null;
    }

    @Override
    protected Region convertToObject(ResultSet rs) {
        Region region = new Region();
        try {
            region.setId(rs.getLong("id"));
            region.setName(rs.getString("name"));
            region.setCode(rs.getString("code"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            region = null;
        }
        return region;
    }
}
