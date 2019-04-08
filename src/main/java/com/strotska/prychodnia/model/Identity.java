package com.strotska.prychodnia.model;

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
    private Set<String> roles;

    public Identity() {
    }

    public Identity(@NotEmpty String username, @NotEmpty String password, @NotNull Set<String> roles) {
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

    public Set<String> getRoles() {
        return roles;
    }
}
