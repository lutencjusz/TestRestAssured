package org.example.courserestassured.data;

import org.example.courserestassured.data.generators.IDataGenerator;
import org.example.courserestassured.data.generators.RandomGenerator;
import org.example.courserestassured.data.generators.StaticGenerator;
import org.example.courserestassured.data.generators.UserGenerator;
import org.example.courserestassured.data.models.ProjectData;
import org.example.courserestassured.data.models.TaskData;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    private final List<IDataGenerator> generators = new ArrayList<>();

    public TestDataGenerator() {
        generators.add(new StaticGenerator());
        generators.add(new RandomGenerator());
        generators.add(new UserGenerator("Przykładowy projekt serializacji", "Przykładowe zadanie serializacji"));

    }

    public ProjectData createProjectData() {
        return selectGenerator().getProjectData();
    }

    public TaskData createTaskData() {
        return selectGenerator().getTaskData();
    }

    private IDataGenerator selectGenerator() {
        String dataType = System.getProperty("generator", "static");
        for (IDataGenerator generator : generators) {
            if (generator.getDataType().equals(dataType)) {
                return generator;
            }
        }
        throw new IllegalArgumentException("Nieznany typ generatora: " + dataType);
    }
}
