package com.orgname.skeleton.services;

import com.jayway.restassured.path.json.JsonPath;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PoopTest {

    @Test
    public void shouldFoo() {
        JsonPath jsonPath = new JsonPath("{\"lol\": 1, \"butt\": {\"fart\" : true}}");
        assertThat(jsonPath.get("butt.fart"), is(1));
    }
}
