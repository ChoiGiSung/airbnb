package com.codesquad.coco.domain.oauth.gitoauth;

public enum GitURI {

    ACCESS_TOKEN_URI("https://github.com/login/oauth/access_token"),
    USER_INFO_URI("https://api.github.com/user");

    private String uri;

    GitURI(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
