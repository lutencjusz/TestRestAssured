package org.example.courserestassured.data.generators;

import org.example.courserestassured.data.models.ProjectData;
import org.example.courserestassured.data.models.TaskData;

public class StaticGenerator implements IDataGenerator {

    @Override
    public ProjectData getProjectData() {
        return new ProjectData("Nowy projekt");
    }

    @Override
    public TaskData getTaskData() {
        return new TaskData("Nowe zadanie");
    }

    @Override
    public String getDataType() {
        return "static";
    }
}
