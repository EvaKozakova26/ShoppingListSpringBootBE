package cz.uhk.ppro.demo.Model;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Indexed
@Table(name = "user", schema = "demo")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String password;

    @ManyToOne
    private Role role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
