package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TextListSection extends Section {

    private List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }

    public void addItem(String item) {
        this.list.add(item);
    }
}
