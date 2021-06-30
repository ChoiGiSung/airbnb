package com.codesquad.coco.utils.mapper;

import com.codesquad.coco.domain.city.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new City(
                rs.getString("name"),
                rs.getString("image_url")
        );
    }
}
