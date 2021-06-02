package com.codesquad.coco.oauth.gitoauth;

import oauth.AccessToken;
import oauth.Oauth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static com.codesquad.coco.oauth.gitoauth.GitURI.ACCESS_TOKEN_URI;
import static com.codesquad.coco.oauth.gitoauth.GitURI.USER_INFO_URI;

@Component
public class GitOauth extends Oauth {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public GitOauth() {
        this.accessTokenUri = ACCESS_TOKEN_URI.getUri();
        this.userInfoUri = USER_INFO_URI.getUri();
    }

    // code 요청은 FE나 ios가 직접한다.
    @Override
    public GitUserInfoDTO requestUserInfo(AccessToken token) {
        HttpHeaders headers = httpHeaderTypeJson();
        headers.setBearerAuth(token.getAccessToken());
        HttpEntity<?> userInfo = new HttpEntity<>(headers);
        return getTemplate().exchange(userInfoUri, HttpMethod.GET, userInfo, GitUserInfoDTO.class).getBody();
    }

    public void changeType(GitHubDeviceType gitHubDeviceType) {
        this.clientId = gitHubDeviceType.getClientKey();
        this.clientSecret = gitHubDeviceType.getClientSecret();
        this.redirectUri = gitHubDeviceType.getRedirectUri();
    }
}
