package com.codesquad.coco.user;

import com.codesquad.coco.global.auth.Auth;
import com.codesquad.coco.global.auth.UserId;
import com.codesquad.coco.oauth.ServerKey;
import com.codesquad.coco.oauth.gitoauth.GitHubDeviceType;
import com.codesquad.coco.oauth.gitoauth.GitUserInfoDTO;
import jwt.JWT;
import jwt.JWTUtils;
import org.springframework.web.bind.annotation.*;

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

    @Auth
    @GetMapping("/v1/users/git-info")
    public GitUserInfoDTO gitUserInfoDTO(@UserId Long userID) {
        return userService.getGitUserInfo(userID);
    }
}
