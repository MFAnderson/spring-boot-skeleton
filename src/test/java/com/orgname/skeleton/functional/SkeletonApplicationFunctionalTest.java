package com.orgname.skeleton.functional;

import com.orgname.skeleton.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class SkeletonApplicationFunctionalTest {

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
