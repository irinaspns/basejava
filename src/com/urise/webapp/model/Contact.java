package com.urise.webapp.model;

import java.util.Objects;

public class Contact {

    private Object label;
    private String url;
    private final String value;

    public Contact(String value) {
        assert value == null : " Contact value can not be null";
        this.value = value;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(label, contact.label) &&
                Objects.equals(url, contact.url) &&
                value.equals(contact.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, url, value);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "label=" + label +
                ", url='" + url + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
