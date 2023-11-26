package org.example.courserestassured.data.generators;

import org.example.courserestassured.data.models.ProjectData;
import org.example.courserestassured.data.models.TaskData;

public interface IDataGenerator {
    ProjectData getProjectData();

    TaskData getTaskData();

    String getDataType();
}
