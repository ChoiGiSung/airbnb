package com.codesquad.coco.user;

import com.codesquad.coco.oauth.gitoauth.GitUserInfoDTO;
import oauth.AccessToken;
import oauth.UserInfoDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import static com.codesquad.coco.utils.sql.UserSQLKt.*;

@Component
public class UserDAO {

    private NamedParameterJdbcTemplate template;

    public UserDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void insertUser(GitUserInfoDTO userInfo, AccessToken token) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("github_id", userInfo.getId())
                .addValue("login", userInfo.getLogin())
                .addValue("email", userInfo.getEmail())
                .addValue("access_token", token.getAccessToken())
                .addValue("profile_image_url", userInfo.getAvatarUrl());

        template.update(INSERT_USER, parameter);
    }

    public int countUserById(UserInfoDTO userInfoDTO) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("github_id", userInfoDTO.getId());
        Integer count = template.queryForObject(FIND_USER_COUNT_BY_GITHUB_ID, parameter, Integer.class);
        return count;
    }

    public void updateUser(GitUserInfoDTO userInfo, AccessToken token) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("github_id", userInfo.getId())
                .addValue("login", userInfo.getLogin())
                .addValue("email", userInfo.getEmail())
                .addValue("access_token", token.getAccessToken())
                .addValue("profile_image_url", userInfo.getAvatarUrl());

        template.update(UPDATE_USER_BY_GITHUB_ID, parameter);
    }
}
