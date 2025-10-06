package com.example.multi;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository {
    private final JdbcTemplate jdbc;

    public TestRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<DataRecord> findAll() {
        String sql = "SELECT id, name FROM data";
        return jdbc.query(sql, (rs, rowNum) ->
                new DataRecord(rs.getInt("id"), rs.getString("name"))
        );
    }

    public void deleteAll() {
        String sql = "DELETE FROM data";
        jdbc.update(sql);
    }

    public void post(String s){
        String sql = "INSERT INTO data (name) VALUES (?)";
        jdbc.update(sql,s);
    }
}


