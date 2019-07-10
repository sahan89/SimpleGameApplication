package com.sahan.netent.service.serviceImpl;

import static io.restassured.RestAssured.get;
import static org.hamcrest.core.IsEqual.equalTo;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class PreviousResultServiceTest {
    private static final int UNIQUE_ID = 719;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085;
    }

    @Test
    public void testResultFetchesSuccess() {
        get("rest/resultController/result?uniqueId=719")
                .then()
                .body("uniqueId", equalTo(UNIQUE_ID));
    }
}
