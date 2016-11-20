package com.example.rest.checklist;

import com.example.rest.util.BooleanToDBConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by czrif on 11/8/2016.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CHECKLIST_ID")
    @NotNull(message = "Needs to map to a Checklist")
    @JsonBackReference
    private CheckList checkList;

    @NotNull(message = "Contents cannot be empty")
    private String contents;

    @NotNull(message = "Sort cannot be empty")
    private int sort;

    @Convert(converter = BooleanToDBConverter.class)
    private boolean complete;

    public Task() {
    }

    public Task(CheckList checkList, String contents, boolean complete) {
        this.checkList = checkList;
        this.contents = contents;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CheckList getCheckList() {
        return checkList;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}
