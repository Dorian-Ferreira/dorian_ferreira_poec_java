package org.dorianferreira.repository;

import org.dorianferreira.entity.regions.City;
import org.dorianferreira.entity.regions.Departement;
import org.dorianferreira.entity.regions.Region;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public class CityRepository extends AbstractRepository<City> {

    private static CityRepository instance;
    private static final PostalCodeRepository pcr = PostalCodeRepository.getRepository();
    private static final DepartementRepository dr = DepartementRepository.getRepository();

    private CityRepository() {
        setTable("city");
    }

    public static CityRepository getRepository() {
        if(instance == null) {
            instance = new CityRepository();
        }
        return instance;
    }

    public List<City> findCityByDepartement(Departement departement) {
        return getBy(new HashMap<>(){{
            put("department_id", departement.getId());
        }}, null, null);
    }

    @Override
    protected City insert(City toSave) {
        return null;
    }

    @Override
    protected City update(City toSave) {
        return null;
    }

    @Override
    protected City convertToObject(ResultSet rs) {
        City city = new City();
        try {
            city.setId(rs.getLong("id"));
            city.setName(rs.getString("name"));
            city.setSiren(rs.getString("siren"));
            city.setCode(rs.getString("code"));
            city.setPopulation(rs.getInt("population"));
            pcr.getBy(new HashMap<>(){{
                    put("city_id", rs.getLong("id"));
                }}, null, null).forEach(city::addPostalCode);
            city.setDepartement(dr.getOne(new HashMap<>(){{
                put("id", rs.getLong("department_id"));
            }}));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            city = null;
        }
        return city;
    }
}
