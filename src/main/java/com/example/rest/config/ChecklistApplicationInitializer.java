package com.example.rest.config;

import com.example.rest.checklist.CheckListRepository;
import com.example.rest.checklist.CheckList;
import com.example.rest.checklist.CheckListItem;
import com.example.rest.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by czrif on 11/8/2016.
 */
@Component
public class ChecklistApplicationInitializer implements CommandLineRunner {

    CheckListRepository listRepo;

    public ChecklistApplicationInitializer(CheckListRepository listRepo) {
        this.listRepo = listRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        List<CheckList> lists = Stream.of("first list", "second list", "3rd list")
                .map(s -> new CheckList(s))
                .peek(s -> s.addCheckListItem(new CheckListItem(null, "Hello from " + s.getTitle(), false)))
                .peek(s -> s.setOwner(new User(0l)))
                .collect(Collectors.toList());

        listRepo.save(lists);
    }
}
