package com.codesquad.coco.global.auth;

import com.codesquad.coco.global.exception.auth.UnknownDevice;
import com.codesquad.coco.domain.oauth.gitoauth.GitHubDeviceType;
import org.springframework.core.convert.converter.Converter;

public class GitHubDeviceTypeConvertor implements Converter<String, GitHubDeviceType> {

    @Override
    public GitHubDeviceType convert(String from) {
        try {
            return GitHubDeviceType.valueOf(from.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownDevice();
        }
    }

}
