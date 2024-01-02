package org.dorianferreira.repository;

import org.dorianferreira.entity.regions.City;
import org.dorianferreira.entity.regions.Departement;
import org.dorianferreira.entity.regions.Region;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public class DepartementRepository extends AbstractRepository<Departement> {

    private static DepartementRepository instance;
    private static final RegionRepository rr = RegionRepository.getRepository();
    private static final CityRepository cr = CityRepository.getRepository();

    private DepartementRepository() {
        setTable("department");
    }

    public static DepartementRepository getRepository() {
        if(instance == null) {
            instance = new DepartementRepository();
        }
        return instance;
    }

    public List<Departement> findDepartmentsByRegion(Region region) {
        return getBy(new HashMap<>(){{
            put("region_id", region.getId());
        }}, null, null);
    }

    public int findPopulationByDepartement(Departement departement) {
        return cr.findCityByDepartement(departement).stream().mapToInt(City::getPopulation).sum();
    }

    @Override
    protected Departement insert(Departement toSave) {
        return null;
    }

    @Override
    protected Departement update(Departement toSave) {
        return null;
    }

    @Override
    protected Departement convertToObject(ResultSet rs) {
        Departement departement = new Departement();
        try {
            departement.setId(rs.getLong("id"));
            departement.setName(rs.getString("name"));
            departement.setCode(rs.getString("code"));
            departement.setRegion(rr.getOne(new HashMap<>(){{
                put("id", rs.getLong("region_id"));
            }}));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            departement = null;
        }
        return departement;
    }
}
