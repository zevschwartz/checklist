package com.example.rest.web;

import com.example.rest.checklist.CheckList;
import com.example.rest.checklist.CheckListService;
import com.example.rest.exceptions.CheckListNotFoundException;
import com.example.rest.security.CurrentUser;
import com.example.rest.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by czrif on 11/8/2016.
 */
@RestController
@RequestMapping("checklists")
public class CheckListController {

    private CheckListService service;

    public CheckListController(CheckListService service) {
        this.service = service;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckListSummary> getAll(@CurrentUser User user) {
        return service
                .getAllCheckListsByOwner(user.getId())
                .stream()
                .map(c -> new CheckListSummary(c))
                .collect(Collectors.toList());
    }

    @GetMapping("find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CheckList getCheckList(@PathVariable Long id) {
        return service.getCheckListById(id);
    }

    @PostMapping("update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseMessage updateCheckList(@RequestBody CheckList list, @CurrentUser User user) {
        if (list == null || list.getId() == null) {
            return new ResponseMessage("Cannot update list", "No ID found");
        }
        if (user == null || user.getId() == null) {
            return new ResponseMessage("Invalid user", "No ID found");
        }
        list.setOwner(user);
        service.saveCheckList(list);
        String.format("List ID: %d, %s, has been updated", list.getId(), list.getTitle());
        return new ResponseMessage(String.format("List ID: %d, %s, has been updated", list.getId(), list.getTitle()), "");
    }

    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckList newCheckList(@RequestBody CheckList list, @CurrentUser User user) {
        if (list == null) {
            throw new IllegalArgumentException("Checklist data is not readable");
        }
        if (user == null) {
            list.setOwner(user);
        }
        service.createCheckList(list);
        return list;
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseMessage deleteCheckList(@PathVariable long id) {
        CheckList list = service.getCheckListById(id);
        service.deleteCheckList(list);
        return new ResponseMessage(list.getTitle() + " Deleted", "");
    }


    @ExceptionHandler(CheckListNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseMessage errorNotFound(Exception e) {
        return new ResponseMessage("Error", e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage badRequest(Exception e) {
        return new ResponseMessage("Error", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage error(Exception e) {
        return new ResponseMessage("Error", e.getMessage());
    }


}
