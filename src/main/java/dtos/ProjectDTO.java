package dtos;

import entities.Project;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDTO {
    private long id;
    private String name;
    private String description;

    public ProjectDTO(Project project){
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
