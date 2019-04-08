package com.strotska.prychodnia.model;

import com.strotska.prychodnia.security.Role;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Identity extends BaseEntity {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Identity() {
    }

    public Identity(@NotEmpty String username, @NotEmpty String password, @NotNull Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
