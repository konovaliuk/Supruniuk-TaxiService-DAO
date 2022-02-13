package com.taxicall.database.entities;

public class OrderFeedback {
    private long id;
    private String comment;
    private int grade;

    public OrderFeedback(long id, String comment, int grade){
        this.id = id;
        this.comment = comment;
        this.grade = grade;
    }

    public long getID() {return id;}
    public String getComment() { return comment; }
    public int getGrade() { return grade; }
}
