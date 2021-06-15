package dtos;

import entities.ProjectHours;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectHoursDTO {
    private long id;
    private int hoursSpent;
    private int userStory;
    private String description;

    public ProjectHoursDTO(ProjectHours projectHours){
        this.id = projectHours.getId();
        this.hoursSpent = projectHours.getHoursSpent();
        this.userStory = projectHours.getUserStory();
        this.description = projectHours.getDescription();
    }

    public int getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(int hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public int getUserStory() {
        return userStory;
    }

    public void setUserStory(int userStory) {
        this.userStory = userStory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
