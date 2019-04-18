package com.strotska.prychodnia.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Service extends BaseEntity {

    @Column(unique = true)
    private String name;

    public Service() {
    }

    public Service(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
