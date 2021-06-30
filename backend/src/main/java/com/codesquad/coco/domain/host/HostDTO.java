package com.codesquad.coco.domain.host;

public class HostDTO {

    private String name;
    private String profileImageUrl;

    public HostDTO(String name, String profileImageUrl) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public static HostDTO of(Host host) {
        return new HostDTO(
                host.getName(),
                host.getProfileImageUrl()
        );
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
