package org.example.courserestassured.data.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectData {

    private String id;
    private String parent_id;
    private int order;
    private String color;
    private String name;
    private int comment_count;
    private String url;
    private String viewStyle;

    public ProjectData(String projectName) {
        this.name = projectName;
    }

    public ProjectData() {}

}
