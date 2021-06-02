package com.codesquad.coco.oauth.gitoauth;

import com.codesquad.coco.utils.GetPropertyUtil;

public enum GitHubDeviceType {

    IOS("github.ios.client.id", "github.ios.secret", "http://localhost:8080/git/auth/app"),
    DESKTOP("github.desktop.client.id", "github.desktop.secret", "http://localhost:3000/git/auth");

    private String clientKey;
    private String clientSecret;
    private String redirectUri;

    GitHubDeviceType(String clientKey, String clientSecret, String redirectUri) {
        this.clientKey = GetPropertyUtil.getProperty(clientKey);
        this.clientSecret = GetPropertyUtil.getProperty(clientSecret);
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
