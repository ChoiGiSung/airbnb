package com.codesquad.coco.domain.oauth.gitoauth;

import com.codesquad.coco.utils.PropertyUtil;

public enum GitHubDeviceType {

    IOS("github.ios.client.id", "github.ios.secret", "http://localhost:8080/git/auth/app"),
    DESKTOP("github.desktop.client.id", "github.desktop.secret", "http://localhost:8080/git/auth");

    private String clientKey;
    private String clientSecret;
    private String redirectUri;

    GitHubDeviceType(String clientKey, String clientSecret, String redirectUri) {
        this.clientKey = PropertyUtil.getProperty(clientKey);
        this.clientSecret = PropertyUtil.getProperty(clientSecret);
        this.redirectUri = redirectUri;
    }

    public boolean isSame(GitHubDeviceType gitHubDeviceType) {
        return this == gitHubDeviceType;
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    @Override
    public String toString() {
        return "GitHubDeviceType{" +
                "clientKey='" + clientKey + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                '}';
    }
}
