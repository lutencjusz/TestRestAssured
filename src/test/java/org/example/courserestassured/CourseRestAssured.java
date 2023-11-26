package org.example.courserestassured;

import org.example.courserestassured.steps.ProjectSteps;
import org.example.courserestassured.steps.TaskSteps;
import org.junit.jupiter.api.Test;

public class CourseRestAssured extends BaseSetup {

    ProjectSteps projectSteps = new ProjectSteps(testDataManager);
    TaskSteps taskSteps = new TaskSteps(testDataManager);

    @Test
    public void createNewProjectAndTask() {
        projectSteps.userCreatesNewProject();
        projectSteps.userChecksProjectDetails();
        projectSteps.userChecksAllProjects();
        taskSteps.userCreatesNewTask();
        taskSteps.userChecksTaskDetails();
        taskSteps.userChecksAllTasks();
    }


    @Test
    public void createSerializedProjectAndTask() {
        projectSteps.userCreatesSerializedProject();
        projectSteps.userChecksProjectDetails();
        projectSteps.userChecksAllProjects();
        taskSteps.userCreatesSerializedTask();
        taskSteps.userChecksTaskDetails();
        taskSteps.userChecksAllTasks();
    }

    @Test
    public void checkDeserializationProjectAndTask() {
        projectSteps.userCreatesNewProject();
        projectSteps.userChecksProjectDeserialization();
        projectSteps.userChecksProjectDetails();
        taskSteps.userCreatesNewTask();
        taskSteps.userChecksTaskDeserialization();
        taskSteps.userChecksTaskDetails();
    }
}
