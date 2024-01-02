package org.dorianferreira.repository;

import org.dorianferreira.entity.regions.PostalCode;

import java.sql.ResultSet;
import java.util.HashMap;

public class PostalCodeRepository extends AbstractRepository<PostalCode> {

    private static PostalCodeRepository instance;

    private PostalCodeRepository() {
        setTable("postal_code");
    }

    public static PostalCodeRepository getRepository() {
        if(instance == null) {
            instance = new PostalCodeRepository();
        }
        return instance;
    }

    @Override
    protected PostalCode insert(PostalCode toSave) {
        return null;
    }

    @Override
    protected PostalCode update(PostalCode toSave) {
        return null;
    }

    @Override
    protected PostalCode convertToObject(ResultSet rs) {
        PostalCode postalCode = new PostalCode();
        try {
            postalCode.setId(rs.getLong("id"));
            postalCode.setCode(rs.getString("code"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            postalCode = null;
        }
        return postalCode;
    }
}
