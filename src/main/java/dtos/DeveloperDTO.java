package dtos;

import entities.Developer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeveloperDTO {
    private long id;
    private String name;
    private String email;
    private String phone;

    public DeveloperDTO(Developer developer){
        this.id = developer.getId();
        this.name = developer.getName();
        this.email = developer.getEmail();
        this.phone = developer.getPhone();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
