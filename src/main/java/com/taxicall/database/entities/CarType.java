package com.taxicall.database.entities;

public class CarType {
    private long id;
    private String typename;
    private String description;

    public CarType(long id, String typename, String description){
        this.id = id;
        this.typename = typename;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) { this.typename = typename; }

    public String getDescription() {
        return typename;
    }

    public void setDescription(String description) { this.description = description; }

}
