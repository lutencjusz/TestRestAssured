package org.example.courserestassured.data.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskData {
    private String content;
    private String id;
    private String projectId;
    private String description;
    private int commentCount;
    public TaskData(String taskName) {
        this.content = taskName;
    }
    public TaskData() {}
}
