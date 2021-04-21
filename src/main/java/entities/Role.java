package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Plaul
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    public static final Roles DEFAULT_ROLE = Roles.USER;

    public enum Roles {
        USER,
        ADMIN
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_name", length = 20)
    @Enumerated(EnumType.STRING)
    private Roles roleName;
    
    @ManyToMany(mappedBy = "roles")
    private List<User> userList;

    public Role() {
    }

    public Role(Roles role) {
        this.roleName = role;
    }

    public Roles getRoleName() {
        return roleName;
    }

    public void setRoleName(Roles role) {
        this.roleName = role;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleName == role.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }

    @Override
    public String toString() {
        return roleName.toString();
    }
}