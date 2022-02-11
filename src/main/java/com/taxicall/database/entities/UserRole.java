package com.taxicall.database.entities;

public class UserRole {
    private long id;
    private long user_id;
    private long role_id;

    public UserRole(long id, long user_id, long role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public long getId() {
        return id;
    }

    public long getUserID() {
        return user_id;
    }

    public long getRoleID() {
        return role_id;
    }
}
