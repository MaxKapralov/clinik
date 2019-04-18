package com.strotska.prychodnia.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class Appointment extends BaseEntity {

    private Instant term;

    private boolean free;

    @ManyToOne
    private UserDetails userDetails;

    public Appointment() {
    }

    public Appointment(Instant term, boolean free) {
        this.term = term;
        this.free = free;
    }

    public Instant getTerm() {
        return term;
    }

    public boolean isFree() {
        return free;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
