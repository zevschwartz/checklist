package com.example.rest.checklist;

import com.example.rest.exceptions.CheckListNotFoundException;
import com.example.rest.security.ReadableChecklist;
import com.example.rest.web.CheckListSummary;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by czrif on 11/8/2016.
 */
@Service
@Transactional(readOnly = true)
public class CheckListService {

    private CheckListRepository repo;

    public CheckListService(CheckListRepository repo) {
        this.repo = repo;
    }

    public List<CheckListSummary> getAllCheckLists() {
        List<CheckListSummary> checklists = repo.findAllBy();
        return checklists;
    }

    public List<CheckListSummary> getAllCheckListsByOwner(Long userid) {
        List<CheckListSummary> checklists = repo.findAllByOwner(userid);
        return checklists;
    }

    @ReadableChecklist
    public CheckList getCheckListById(Long id) {
        CheckList result = repo.findOne(id);
        if (result == null) {
            throw new CheckListNotFoundException(id);
        }
        return result;
    }

    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('ADMIN') or this.getCheckListById(#list.getId()).owner.id == principal.id")
    public CheckList saveCheckList(CheckList list) {
        repo.save(list);
        return list;
    }

    @Transactional(readOnly = false)
    @PreAuthorize("#list.id == null")
    public CheckList createCheckList(CheckList list) {
        repo.save(list);
        return list;
    }

    @ReadableChecklist
    public void deleteCheckList(CheckList list) {
        repo.delete(list);
    }

    public CheckList getCheckList(long id) {
        return repo.findOne(id);
    }


}
