package com.inventory.model;

import java.io.Serializable;
import java.util.Objects;

public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String contact;
    private String email;

    public Supplier(String id, String name, String contact, String email) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setContact(String contact) { this.contact = contact; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supplier)) return false;
        return Objects.equals(id, ((Supplier) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return String.format("Supplier[id=%s, name=%s, contact=%s, email=%s]",
                id, name, contact, email);
    }
}