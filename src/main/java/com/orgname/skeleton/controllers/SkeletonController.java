package com.orgname.skeleton.controllers;

import com.orgname.skeleton.exceptions.NotFoundException;
import com.orgname.skeleton.resources.Message;
import com.orgname.skeleton.resources.Skeleton;
import com.orgname.skeleton.services.SkeletonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SkeletonController {

    //for HATEOAS links
    public static final SkeletonController THIS = methodOn(SkeletonController.class);

    Logger logger = LoggerFactory.getLogger(SkeletonController.class);

    @Autowired
    SkeletonService skeletonService;


    @RequestMapping(path = "/greet", method = RequestMethod.GET)
    public Message hello(@RequestParam Optional<String> thing) {
        logger.debug("We're doing things");
        return new Message(String.format("Hello, %s.", thing.orElse("World")));
    }

    @RequestMapping(path = "/skeletons", method = RequestMethod.POST)
    public Skeleton create(@RequestParam String name) {
        Skeleton skeleton = skeletonService.createSkeleton(name);
        skeleton.add(linkTo(THIS.get(name)).withSelfRel());
        return skeleton;
    }
    @RequestMapping(path = "/skeletons/{name}", method = RequestMethod.GET)
    public Skeleton get(@PathVariable @NotNull String name) {
        Skeleton skeleton = skeletonService.getByName(name).orElseThrow(() -> new NotFoundException("No skeleton with name " + name));
        skeleton.add(linkTo(THIS.get(name)).withSelfRel());
        return skeleton;
    }

}
