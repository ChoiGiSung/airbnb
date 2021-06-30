package com.codesquad.coco.domain.image;

import com.codesquad.coco.utils.mapper.ImageMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codesquad.coco.utils.sql.ImageSQLKt.FIND_IMAGE_BY_ROOM_ID;

@Component
public class ImageDAO {

    private NamedParameterJdbcTemplate template;

    public ImageDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public List<Image> findAllImageByRoomId(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("room_id", id);
        return template.query(FIND_IMAGE_BY_ROOM_ID, parameter, new ImageMapper());
    }
}
