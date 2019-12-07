package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeBlock {

    private LocalDate from;
    private LocalDate till;
    private String subTitel;
    private List<String> description = new ArrayList<>();

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTill() {
        return till;
    }

    public void setTill(LocalDate till) {
        this.till = till;
    }

    public String getSubTitel() {
        return subTitel;
    }

    public void setSubTitel(String subTitel) {
        this.subTitel = subTitel;
    }

    public List<String> getDescription() {
        return description;
    }

    public void addDescription(String description) {
        this.description.add(description);
    }
}
