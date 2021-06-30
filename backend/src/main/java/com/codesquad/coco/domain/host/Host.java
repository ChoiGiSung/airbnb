package com.codesquad.coco.domain.host;

import org.springframework.data.annotation.Id;

public class Host {

    @Id
    private Long id;

    private String name;
    private String profileImageUrl;

    public Host(String name, String profileImageUrl) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @Override
    public String toString() {
        return "Host{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }
}
