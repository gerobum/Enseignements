package edu.uha.miage.config.security.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Authorities implements Serializable {
    @NotNull  
    @Id
    private String authority;

    @OneToMany(mappedBy = "authorities")
    private List<Users> users;

    public Authorities() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
    
    
}
