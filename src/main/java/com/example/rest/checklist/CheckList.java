package com.example.rest.checklist;

import com.example.rest.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by czrif on 11/8/2016.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 40, message = "title must have between 4 and 40 characters")
    private String title;

    @OrderColumn(name = "sort")
    @OneToMany(orphanRemoval = true, mappedBy = "checkList", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> checkListItems;

    @CreatedBy
    @OneToOne
    private User owner;

    @CreatedDate
    @Column(insertable = true, updatable = false)
    private LocalDateTime createdOn;

    @LastModifiedDate
    private LocalDateTime lastModifiedOn;

    @LastModifiedBy
    @OneToOne
    private User lastModifiedBy;

    protected CheckList() {
    }

    public CheckList(String title) {
        this.title = title;
    }

    public CheckList(long id, String title) {
        this.id = id;
        this.title = title;
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

    public List<Task> getCheckListItems() {
        return checkListItems;
    }

    public void setCheckListItems(List<Task> checkListItems) {
        checkListItems.forEach(i -> i.setCheckList(this));
        this.checkListItems = checkListItems;
    }

    public void addCheckListItem(Task checkListItem) {
        if (checkListItems == null) {
            checkListItems = new ArrayList<>();
        }
        checkListItem.setCheckList(this);
        checkListItems.add(checkListItem);
    }

    public String getCreatedOn() {
        return createdOn == null ? null : createdOn.toString();
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastModifiedOn() {
        return lastModifiedOn == null ? null : lastModifiedOn.toString();
    }

    public void setLastModifiedOn(LocalDateTime lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckList)) return false;

        CheckList checkList = (CheckList) o;

        if (getId() != checkList.getId()) return false;
        if (getTitle() != null ? !getTitle().equals(checkList.getTitle()) : checkList.getTitle() != null) return false;
        if (getCheckListItems() != null ? !getCheckListItems().equals(checkList.getCheckListItems()) : checkList.getCheckListItems() != null)
            return false;
        if (getOwner() != null ? !getOwner().equals(checkList.getOwner()) : checkList.getOwner() != null) return false;
        return getCreatedOn() != null ? getCreatedOn().equals(checkList.getCreatedOn()) : checkList.getCreatedOn() == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        return result;
    }
}
