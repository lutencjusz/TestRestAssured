package org.example.courserestassured.data.generators;

import com.github.javafaker.Faker;
import org.example.courserestassured.data.models.ProjectData;
import org.example.courserestassured.data.models.TaskData;

public class RandomGenerator implements IDataGenerator {
    private static final Faker faker = new Faker();

    @Override
    public ProjectData getProjectData() {
        return new ProjectData(faker.witcher().monster());
    }

    @Override
    public TaskData getTaskData() {
        return new TaskData(faker.witcher().witcher());
    }

    @Override
    public String getDataType() {
        return "random";
    }
}
