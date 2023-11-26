package org.example.courserestassured.task;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.courserestassured.CommonClient;
import org.example.courserestassured.data.models.TaskData;

public class TaskClient extends CommonClient {

    public Response sendCreateRequest(String taskName, String projectId) {

        String payload = String.format("{\"content\": \"%s\",\"project_id\":\"%s\"}", taskName, projectId);
        RequestSpecification requestSpec = getRequestSpecBuilder()
                .setBody(payload)
                .build();

        return sendRequest(Method.POST, requestSpec, "/tasks");
    }

    public Response userChecksTaskDetails(String taskId) {

        RequestSpecification requestSpec = getRequestSpecBuilder()
                .addPathParam("id", taskId)
                .build();

        return sendRequest(Method.GET, requestSpec, "/tasks/{id}");
    }

    public Response userChecksAllTasks() {
        RequestSpecification requestSpec = getRequestSpecBuilder()
                .build();
        return sendRequest(Method.GET, requestSpec, "/tasks");
    }

    public Response sendCreateRequest(TaskData payload) {

        RequestSpecification requestSpec = getRequestSpecBuilder()
                .setBody(payload)
                .build();

        return sendRequest(Method.POST, requestSpec, "/tasks");
    }
}
