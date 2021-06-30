package com.codesquad.coco.controller;

import com.codesquad.coco.domain.oauth.ServerKey;
import com.codesquad.coco.domain.oauth.gitoauth.GitHubDeviceType;
import com.codesquad.coco.domain.oauth.gitoauth.GitUserInfoDTO;
import com.codesquad.coco.service.UserService;
import jwt.JWT;
import jwt.JWTUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private ServerKey serverKey;
    private UserService userService;

    public LoginController(ServerKey serverKey, UserService userService) {
        this.serverKey = serverKey;
        this.userService = userService;
    }

    @PostMapping("/v1/auth/{gitHubDeviceType}")
    public JWT login(@RequestParam String code, @PathVariable GitHubDeviceType gitHubDeviceType) {
        GitUserInfoDTO userInfo = userService.loginByGitOauth(code, gitHubDeviceType);
        String jwt = JWTUtils.createJWTTypeBearer(userInfo, serverKey.getJwtServerKey());
        return new JWT(jwt);
    }
}
