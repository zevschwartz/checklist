package com.example.rest.web;

import com.example.rest.checklist.CheckList;

/**
 * Created by czrif on 11/10/2016.
 */
public class CheckListSummary {
    private Long id;

    private String title;

    public CheckListSummary() {
    }

    public CheckListSummary(CheckList list) {
        this.id = list.getId();
        this.title = list.getTitle();
    }

    public CheckListSummary(CheckListSummary list) {
        this.id = list.getId();
        this.title = list.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
