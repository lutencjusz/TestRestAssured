package org.example.courserestassured.data;

import lombok.var;
import org.example.courserestassured.data.models.ProjectData;
import org.example.courserestassured.data.models.TaskData;

import java.util.HashMap;
import java.util.Map;

public class TestDataManager {

    private TestDataGenerator testDataGenerator = new TestDataGenerator();

    private Map<String, Object> data = new HashMap<>();

    public void setTestData(String key, Object value) {
        data.put(key, value);
    }

    public Object getTestData(String key) {
        return data.get(key);
    }

    public ProjectData createNewProjectData() {
        var projectData = testDataGenerator.createProjectData();
        setTestData("projectData", projectData);
        return projectData;
    }

    public TaskData createNewTaskData() {
        var taskData = testDataGenerator.createTaskData();
        setTestData("taskData", taskData);
        return taskData;
    }
}
