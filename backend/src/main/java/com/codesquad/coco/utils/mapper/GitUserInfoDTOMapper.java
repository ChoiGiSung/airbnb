package com.codesquad.coco.utils.mapper;

import com.codesquad.coco.oauth.gitoauth.GitUserInfoDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GitUserInfoDTOMapper implements RowMapper<GitUserInfoDTO> {
    @Override
    public GitUserInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GitUserInfoDTO(
                rs.getLong("id"),
                rs.getString("profile_image_url"),
                rs.getString("email"),
                rs.getString("login")
        );
    }
}
