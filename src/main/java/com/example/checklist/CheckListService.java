package com.example.checklist;

import com.example.checklist.web.CheckListSummary;
import com.example.checklist.exceptions.CheckListNotFoundException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by czrif on 11/8/2016.
 */
@Service
@Transactional
public class CheckListService {

    private CheckListRepo repo;

    public CheckListService(CheckListRepo repo) {
        this.repo = repo;
    }

    public List<CheckListSummary> getAllCheckLists() {
        List<CheckListSummary> checklists = repo.findAllBy();
        return checklists;
    }

    @PostAuthorize("hasRole('ADMIN') or returnObject.owner == principal.username")
    public CheckList getCheckListById(Long id) {
        CheckList result = repo.findOne(id);
        if (result == null) {
            throw new CheckListNotFoundException(id);
        }
        return result;
    }

    @PreAuthorize("this.getCheckListById(#list.getId()).owner == principal.username or hasRole('ADMIN')")
    public CheckList saveCheckList(CheckList list) {
        repo.save(list);
        return list;
    }

    @PreAuthorize("#list.id == null")
    public CheckList createCheckList(CheckList list) {
        repo.save(list);
        return list;
    }

    @PreAuthorize("hasRole('ADMIN') || #list.owner == principal.username")
    public void deleteCheckList(CheckList list) {
        repo.delete(list);
    }

    public CheckList getCheckList(long id) {
        return repo.findOne(id);
    }


}
