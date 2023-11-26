package org.example.courserestassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.var;

import java.io.InputStream;
import java.util.Properties;

public class CommonClient {
    private static final Properties properties;

    static {
        var env = System.getProperty("env", "PROD");
        var fileName = env + ".env.properties";

        InputStream propertiesFile = CommonClient.class.getClassLoader().getResourceAsStream(fileName);
        properties = new java.util.Properties();
        try {
            properties.load(propertiesFile);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected RequestSpecBuilder getRequestSpecBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(properties.getProperty("baseUri"))
                .setBasePath(properties.getProperty("basePath"))
                .addHeader("Authorization", properties.getProperty("Authorization"));
    }

    protected Response sendRequest(Method method, RequestSpecification requestSpec, String path) {
        return RestAssured
                .given()
                .spec(requestSpec)
                .when()
//                .log().all()
                .request(method, path);


    }
}
