package com.codesquad.coco.domain.oauth.gitoauth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import oauth.UserInfoDTO;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GitUserInfoDTO extends UserInfoDTO {

    private String avatarUrl;
    private String email;
    private String login;

    public GitUserInfoDTO() {
    }

    public GitUserInfoDTO(Long id, String avatarUrl, String email, String login) {
        super(id);
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "GitUserInfoDTO{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
