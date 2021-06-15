package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "project_developers",
    joinColumns = {@JoinColumn(name = "fk_developer_id")},
    inverseJoinColumns = {@JoinColumn(name = "fk_project_id")})

    private List<Developer> developers;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "billedBy")
    private List<ProjectHours> projectHours;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
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

    public List<Developer> getDevelopers() {
        return developers;
    }

    public Developer addDeveloper(Developer developer) {
        this.developers.add(developer);
        return developer;
    }

    public List<ProjectHours> getProjectHours() {
        return projectHours;
    }

    public void setProjectHours(List<ProjectHours> projectHours) {
        this.projectHours = projectHours;
    }
}
