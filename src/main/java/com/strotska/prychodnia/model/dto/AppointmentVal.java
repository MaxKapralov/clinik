package com.strotska.prychodnia.model.dto;

import java.time.Instant;

public class AppointmentVal {
    private Long id;
    private Instant term;
    private boolean free;

    public AppointmentVal(Long id, Instant term, boolean free) {
        this.id = id;
        this.term = term;
        this.free = free;
    }

    public Long getId() {
        return id;
    }

    public Instant getTerm() {
        return term;
    }

    public boolean isFree() {
        return free;
    }
}