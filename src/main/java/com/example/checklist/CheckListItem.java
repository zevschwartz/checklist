package com.example.checklist;

import com.example.checklist.db.utils.BooleanToDBConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by czrif on 11/8/2016.
 */
@Entity
public class CheckListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CHECKLIST_ID")
    @NotNull
    @JsonIgnore
    private CheckList checkList;

    @NotNull
    private String contents;

    @NotNull
    private int sort;

    @Convert(converter = BooleanToDBConverter.class)
    private boolean complete;

    public CheckListItem() {
    }

    public CheckListItem(CheckList checkList, String contents, boolean complete) {
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
