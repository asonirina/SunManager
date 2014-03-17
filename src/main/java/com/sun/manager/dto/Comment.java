package com.sun.manager.dto;

import java.util.Date;

public class Comment {

    private Long id;
    private Date date;
    private String comment;

    public Comment() {
    }

    public Comment(Date date, String comment) {
        this.date = date;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
