package org.example.courserestassured.project;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.bytebuddy.asm.Advice;
import org.example.courserestassured.CommonClient;
import org.example.courserestassured.data.models.ProjectData;

public class ProjectClient extends CommonClient {

    public Response sendCreateRequest(String projectName) {

        String payload = String.format("{\"name\": \"%s\"}", projectName);
        RequestSpecification requestSpec = getRequestSpecBuilder()
                .setBody(payload)
                .build();

        return sendRequest(Method.POST, requestSpec, "/projects");
    }

    public Response userChecksProjectDetails(String projectId) {

        RequestSpecification requestSpec = getRequestSpecBuilder()
                .addPathParam("id", projectId)
                .build();

        return sendRequest(Method.GET, requestSpec, "/projects/{id}");
    }

    public Response userChecksAllProjects() {
        RequestSpecification requestSpec = getRequestSpecBuilder()
                .build();
        return sendRequest(Method.GET, requestSpec, "/projects");
    }

    public Response sendCreateRequest (ProjectData payload){
        RequestSpecification requestSpec = getRequestSpecBuilder()
                .setBody(payload)
                .build();
        return sendRequest(Method.POST, requestSpec, "/projects");
    }
}
