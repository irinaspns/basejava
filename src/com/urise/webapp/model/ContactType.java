package com.urise.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Тел."),
    SKYPE("Skype"),
    MAIL("Почта"),
    LINKED_IN("in"),
    GIT_HUB("git"),
    STACK_OVERFLOW("stack"),
    HOME_PAGE("home page");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
