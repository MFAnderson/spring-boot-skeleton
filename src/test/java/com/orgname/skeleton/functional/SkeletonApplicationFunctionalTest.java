package com.orgname.skeleton.functional;

import com.orgname.skeleton.Application;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class SkeletonApplicationFunctionalTest {
    //THESE TESTS CURRENTLY DONT PASS BECAUSE I'M A DOOFUS BUT I'M COMMITTING THEM ANYWAY
    @Test
    public void shouldGreetTheSpecifiedThing() {
        given()
                .param("thing", "Ghost")
        .when()
                .get("/")
        .then()
                .body("content", is("Hello, Ghost."));
    }

    @Test
    public void shouldGreetWorldIfNoThingSpecified() {
        given()
                //no value for "thing"
        .when()
                .get("/")
        .then()
                .body("content", is("Hello, World."));
    }

    @Test
    public void shouldGet404ForNonExistentSkeleton() {
        when()
                .get("/skeletons/{name}", "Nobody")
        .then()
                .statusCode(404);
    }

    @Test
    public void shouldGetASkeletonThatHasBeenCreated() {
        String aSkeletonName = "Casper";

        given()
                .param("name", aSkeletonName).post("/skeletons");

        when()
                .get("/skeletons/{name}", aSkeletonName)
        .then()
                .body("name", is(aSkeletonName));
    }
}
