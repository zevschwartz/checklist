package com.example.rest.config;

import com.example.rest.checklist.CheckList;
import com.example.rest.checklist.CheckListRepository;
import com.example.rest.checklist.Task;
import com.example.rest.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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
        SecurityContextHolder.getContext().setAuthentication(new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        });
        List<CheckList> lists = Stream.of("first list", "second list", "3rd list")
                .map(s -> new CheckList(s))
                .peek(s -> s.addCheckListItem(new Task(null, "Hello from " + s.getTitle(), false)))
                .peek(s -> s.setOwner(new User(ThreadLocalRandom.current().nextLong(0,4

                ))))
                .collect(Collectors.toList());

        listRepo.save(lists);
    }
}
