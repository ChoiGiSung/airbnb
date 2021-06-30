package com.codesquad.coco.utils.mapper;

import com.codesquad.coco.domain.image.Image;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Image(
                rs.getString("i_url"),
                rs.getString("i_type")
        );
    }
}
