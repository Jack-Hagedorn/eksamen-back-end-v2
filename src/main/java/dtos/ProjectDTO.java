package dtos;

import entities.Project;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ProjectDTO {
    private long id;
    private String name;
    private String description;
    private List<DeveloperDTO> developers;

    public ProjectDTO(Project project){
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.developers = new ArrayList<>();
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

    public List<DeveloperDTO> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<DeveloperDTO> developers) {
        this.developers = developers;
    }
}
