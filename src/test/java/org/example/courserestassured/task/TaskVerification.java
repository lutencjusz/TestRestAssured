package org.example.courserestassured.task;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.courserestassured.data.models.TaskData;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class TaskVerification {

    public void verifyCreateTask(Response response, String expectedTaskName, String expectedProjectId) {
        response
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
        MatcherAssert.assertThat("Sprawdzenie nazwy tasku", response.jsonPath().get("content"), Matchers.equalTo(expectedTaskName));
        MatcherAssert.assertThat("Sprawdzenie Id Projektu", response.jsonPath().get("project_id"), Matchers.equalTo(expectedProjectId));
    }

    public void verifyTaskDetails(Response response, String taskId) {
        response
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", Matchers.equalTo(taskId));
    }

    public void verifyAllTasks(Response response, String taskId) {
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body(String.format("find{it.id = \"%s\"}.id", taskId), Matchers.equalTo(taskId))
                .and()
                .body(String.format("find{it.id = \"%s\"}", taskId), Matchers.notNullValue());
    }

    public void verifyTaskDeserialization(Response response, String taskId, String projectId) {
        TaskData taskData = response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", Matchers.equalTo(taskId))
                .body("projectId", Matchers.equalTo(projectId))
                .and()
                .extract().as(TaskData.class);
        MatcherAssert.assertThat("Sprawdzenie Id taska przez Machera", taskData.getId(), Matchers.equalTo(taskId));
        MatcherAssert.assertThat("Sprawdzenie Id Project przez Machera", taskData.getProjectId(), Matchers.equalTo(projectId));
    }
}
