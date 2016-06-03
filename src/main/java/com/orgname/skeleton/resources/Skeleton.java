package com.orgname.skeleton.resources;

import org.springframework.hateoas.ResourceSupport;

public class Skeleton extends ResourceSupport {

    private final String name;

    public Skeleton(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
