package org.example.courserestassured.data.generators;

import org.example.courserestassured.data.models.ProjectData;
import org.example.courserestassured.data.models.TaskData;

public class UserGenerator implements IDataGenerator{

    private String projectName;
    private String taskName;

    public UserGenerator(String projectName, String TaskName) {
        this.projectName = projectName;
        this.taskName = TaskName;
    }

    @Override
    public ProjectData getProjectData() {
        return new ProjectData(this.projectName);
    }

    @Override
    public TaskData getTaskData() {
        return new TaskData(this.taskName);
    }

    @Override
    public String getDataType() {
        return "user";
    }
}
