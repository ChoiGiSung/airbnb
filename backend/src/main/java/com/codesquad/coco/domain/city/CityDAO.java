package com.codesquad.coco.domain.city;

import com.codesquad.coco.utils.mapper.CityMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codesquad.coco.utils.sql.CitySQLKt.FIND_ALL;

@Component
public class CityDAO {

    private NamedParameterJdbcTemplate template;

    public CityDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public List<City> findAll() {
        return template.query(FIND_ALL, new CityMapper());
    }
}
