package org.example.courserestassured.project;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.var;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.example.courserestassured.data.models.ProjectData;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import static org.apache.commons.lang3.BooleanUtils.and;

public class ProjectVerification {

    public void verifyCreateProject(Response response, String expectedProjectName) {
        response.then().assertThat()
//                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(200);

        MatcherAssert.assertThat("Sprawdzenie nazwy projektu", response.jsonPath().get("name"), Matchers.equalTo(expectedProjectName));
        MatcherAssert.assertThat("Sprawdzenie, czy projekt jest pusty", response.then().extract().path("name"), Matchers.notNullValue());


        // asercje z biblioteki AssertJ, twarda asercja
        Assertions.assertThat(response.jsonPath().getString("name"))
                .as("Sprawdzenie nazwy projektu")
                .isEqualTo(response.jsonPath().get("name"), expectedProjectName);

        // miękka asercja
        var softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.jsonPath().getString("name")).as("Sprawdzenie nazwy projektu").isEqualTo(expectedProjectName);
        softAssertions.assertThat(response.jsonPath().getString("name")).as("Sprawdzenie, czy nazwa jest pusta").isNotBlank();
        softAssertions.assertThat(response.jsonPath().getString("name")).as("Sprawdzenie, czy nazwa jest pusta").isNotEmpty();
        softAssertions.assertAll();


    }

    public void verifyProjectDetails(Response response, String projectId) {
        response.then().assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", Matchers.equalTo(projectId));
    }

    public void verifyAllProjects(Response response, String projectId) {
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body(String.format("find{it.id = \"%s\"}.id", projectId), Matchers.equalTo(projectId))
                .and()
                .body(String.format("find{it.id = \"%s\"}", projectId), Matchers.notNullValue());
    }

    public void verifyProjectDeserialization(Response response, String projectId) {

        ProjectData projectData =response.then()
                    .assertThat()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("id", Matchers.equalTo(projectId))
                    .and()
                        .extract().body().as(ProjectData.class);
//
        MatcherAssert.assertThat("Sprawdzenie id projektu", projectData.getId(), Matchers.equalTo(projectId));
    }
}
