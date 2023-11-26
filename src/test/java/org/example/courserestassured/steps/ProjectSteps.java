package org.example.courserestassured.steps;

import io.restassured.response.Response;
import org.example.courserestassured.data.models.ProjectData;
import org.example.courserestassured.data.TestDataManager;
import org.example.courserestassured.project.ProjectClient;
import org.example.courserestassured.project.ProjectVerification;

public class ProjectSteps {
    ProjectClient projectClient = new ProjectClient();
    ProjectVerification projectVerification = new ProjectVerification();
    TestDataManager testDataManager;

    public void userCreatesNewProject() {
        ProjectData projectData = testDataManager.createNewProjectData();
        Response response = projectClient.sendCreateRequest(projectData.getName());
        projectVerification.verifyCreateProject(response, projectData.getName());
        projectData.setId(response.then().extract().path("id"));
        System.out.println("Project ID: " + projectData.getId() + " in ProjectSteps");
    }

    public void userChecksProjectDetails() {
        ProjectData projectData = (ProjectData) testDataManager.getTestData("projectData");
        Response response = projectClient.userChecksProjectDetails(projectData.getId());
        projectVerification.verifyProjectDetails(response, projectData.getId());
    }

    public void userChecksAllProjects() {
        ProjectData projectData = (ProjectData) testDataManager.getTestData("projectData");
        Response response = projectClient.userChecksAllProjects();
        projectVerification.verifyAllProjects(response, projectData.getId());
    }

    public void userCreatesSerializedProject() {
        ProjectData projectData = testDataManager.createNewProjectData();
        Response response = projectClient.sendCreateRequest(projectData);
        projectVerification.verifyCreateProject(response,projectData.getName());
        projectData.setId(response.then().extract().path("id"));
        System.out.println("Serialized Project ID: " + projectData.getId() + " in ProjectSteps");
    }

    public void userChecksProjectDeserialization() {
        ProjectData projectData = (ProjectData) testDataManager.getTestData("projectData");
        Response response = projectClient.userChecksProjectDetails(projectData.getId());
        projectVerification.verifyProjectDeserialization(response, projectData.getId());
    }

    public ProjectSteps(TestDataManager testDataManager) {
        this.testDataManager = testDataManager;
    }

}
