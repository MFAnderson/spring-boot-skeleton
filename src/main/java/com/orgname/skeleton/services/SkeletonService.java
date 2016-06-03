package com.orgname.skeleton.services;

import com.orgname.skeleton.mapper.SkeletonMapper;
import com.orgname.skeleton.resources.Skeleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SkeletonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkeletonService.class);

    @Autowired
    private SkeletonMapper skeletonMapper;

    @Transactional
    public String getAString() {
        return skeletonMapper.getSingleString();
    }

    public Skeleton createSkeleton(String name) {
        LOGGER.debug("this is where you might have business logic, although maybe not on creating a new resource");
        skeletonMapper.createSkeleton(name);
        return new Skeleton(name);
    }

    public Optional<Skeleton> getByName(String name) {
        return Optional.ofNullable(skeletonMapper.findByName(name));
    }
}
