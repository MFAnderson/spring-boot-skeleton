package com.orgname.skeleton.controllers;

import com.orgname.skeleton.domain.Message;
import com.orgname.skeleton.domain.Skeleton;
import com.orgname.skeleton.exceptions.NotFoundException;
import com.orgname.skeleton.services.SkeletonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class SkeletonController {

    //for HATEOAS links
    public static final SkeletonController THIS = methodOn(SkeletonController.class);

    Logger logger = LoggerFactory.getLogger(SkeletonController.class);

    @Autowired
    SkeletonService skeletonService;


    @RequestMapping(path = "/greet", method = GET)
    public ResponseEntity<Message> hello(@RequestParam Optional<String> thing, Principal user) {
        logger.debug("We're doing things");
        Message message = new Message(String.format("Hello, %s.", thing.orElse(user.getName())));
        return new ResponseEntity<>(message, OK);
    }

    @RequestMapping(path = "/skeletons", method = POST)
    public ResponseEntity<Skeleton> create(@RequestParam String name) {
        Skeleton skeleton = skeletonService.createSkeleton(name);
        skeleton.add(linkTo(THIS.get(name)).withSelfRel());

        return new ResponseEntity<>(skeleton, CREATED);
    }
    @RequestMapping(path = "/skeletons/{name}", method = GET)
    public ResponseEntity<Skeleton> get(@PathVariable @NotNull String name) {
        Skeleton skeleton = skeletonService.getByName(name).orElseThrow(() -> new NotFoundException("No skeleton with name " + name));
        skeleton.add(linkTo(THIS.get(name)).withSelfRel());
        Optional<String> ha = Optional.of("ha");
        Optional<StringBuilder> sb = Optional.of(new StringBuilder());
        Optional<StringBuilder> lol = sb.map(x -> x.append("lol"));
        return new ResponseEntity<>(skeleton, OK);
    }

}
