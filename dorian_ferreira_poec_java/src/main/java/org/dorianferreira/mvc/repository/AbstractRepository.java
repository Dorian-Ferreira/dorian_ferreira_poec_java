package org.dorianferreira.mvc.repository;

import org.dorianferreira.mvc.entity.EntityInterface;
import org.dorianferreira.mvc.service.DBConnect;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class AbstractRepository<T extends EntityInterface> {
    private String table;

    protected Map<String, List<T>> entitiesCache = new HashMap<>();

    protected ResultSet getResult(String query) {
        try {
            Statement stmt = DBConnect.getConnection().createStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<T> getAll() {
        return getBy(null, null, null);
    }

    public List<T> getBy(Map<String, Object> map, Integer limit, Map<String, String> order) {
        List<T> results = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM " + table);
        if(map != null && !map.isEmpty()) {
            sql.append(" WHERE ");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sql.append(entry.getKey().toLowerCase());
                if (entry.getValue() instanceof String) {
                    if(((String)entry.getValue()).contains("BETWEEN")) {
                        sql.append(" ");
                        sql.append(entry.getValue());
                    } else {
                        sql.append(" = ");
                        sql.append("'").append(entry.getValue()).append("'");
                    }
                } else {
                    sql.append(" = ");
                    sql.append(entry.getValue());
                }
                sql.append(" AND ");
            }
            sql.delete(sql.length() - 5, sql.length());
        }

        if(order != null && !order.isEmpty()) {
            for (Map.Entry<String, String> entry : order.entrySet()) {
                sql.append(" ORDER BY ");
                sql.append(entry.getKey());
                sql.append(" ");
                sql.append(entry.getValue());
                sql.append(", ");
            }
            sql.delete(sql.length()-2, sql.length());
        }

        if(limit != null) {
            sql.append(" LIMIT ");
            sql.append(limit);
            sql.append(" ");
        }

        if(!entitiesCache.containsKey(sql.toString())) {
            try {
                ResultSet rs = getResult(sql.toString());
                if(rs == null) {
                    return results;
                }
                while (rs.next()) {
                    results.add(convertToObject(rs));
                }

                entitiesCache.put(sql.toString(), results);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return entitiesCache.get(sql.toString());
    }

    public T getOne(Map<String, Object> map) {
        try {
            return getBy(map, null, null).get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void delete(Long id) {
        String sql = "DELETE FROM " + table + " WHERE id = "+ id;
        getResult(sql);
    }

    public T save(T toSave) {
        if(toSave.getId() == null){
            return insert(toSave);
        }
        return update(toSave);
    }

    protected abstract T insert(T toSave);
    protected abstract T update(T toSave);
    protected abstract T convertToObject(ResultSet rs);
}