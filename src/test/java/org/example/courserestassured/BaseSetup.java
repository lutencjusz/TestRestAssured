package org.example.courserestassured;

import io.restassured.RestAssured;
import org.example.courserestassured.data.TestDataManager;
import org.junit.jupiter.api.BeforeAll;

public class BaseSetup {

    public TestDataManager testDataManager = new TestDataManager();

    @BeforeAll
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
