package org.example.courserestassured.steps;

import io.restassured.response.Response;
import org.example.courserestassured.data.models.ProjectData;
import org.example.courserestassured.data.models.TaskData;
import org.example.courserestassured.data.TestDataManager;
import org.example.courserestassured.task.TaskClient;
import org.example.courserestassured.task.TaskVerification;

public class TaskSteps {
    TaskClient taskClient = new TaskClient();
    TaskVerification taskVerification = new TaskVerification();
    TestDataManager testDataManager;

    public void userCreatesNewTask() {
        ProjectData projectData = (ProjectData) testDataManager.getTestData("projectData");
        TaskData taskData = testDataManager.createNewTaskData();
        Response response = taskClient.sendCreateRequest(taskData.getContent(), projectData.getId());
        taskVerification.verifyCreateTask(response, taskData.getContent(), projectData.getId());
        taskData.setId(response.then().extract().path("id"));
        System.out.println("Project ID: " + projectData.getId() + ", Task ID: " + taskData.getId() + " in TaskSteps");
    }

    public void userChecksTaskDetails() {
        TaskData taskData = (TaskData) testDataManager.getTestData("taskData");
        Response response = taskClient.userChecksTaskDetails(taskData.getId());
        taskVerification.verifyTaskDetails(response, taskData.getId());
    }

    public void userChecksAllTasks() {
        TaskData taskData = (TaskData) testDataManager.getTestData("taskData");
        Response response = taskClient.userChecksAllTasks();
        taskVerification.verifyAllTasks(response, taskData.getId());
    }

    public void userCreatesSerializedTask() {
        ProjectData projectData = (ProjectData) testDataManager.getTestData("projectData");
        TaskData taskData = testDataManager.createNewTaskData();
        taskData.setProjectId(projectData.getId());
        Response response = taskClient.sendCreateRequest(taskData);
        taskVerification.verifyCreateTask(response, taskData.getContent(), projectData.getId());
        taskData.setId(response.then().extract().path("id"));
        System.out.println("Serialized Project ID: " + projectData.getId() + ", Serialized Task ID: " + taskData.getId() + " in TaskSteps");
    }

    public void userChecksTaskDeserialization() {
        TaskData taskData = (TaskData) testDataManager.getTestData("taskData");
        Response response = taskClient.userChecksTaskDetails(taskData.getId());
        taskVerification.verifyTaskDeserialization(response, taskData.getId(), taskData.getProjectId());
    }

    public TaskSteps(TestDataManager testDataManager) {
        this.testDataManager = testDataManager;
    }
}
