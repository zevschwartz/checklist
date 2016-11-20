package com.example.rest.security;

import com.example.rest.checklist.CheckList;
import com.example.rest.user.User;
import org.springframework.stereotype.Component;

/**
 * Created by czrif on 11/20/2016.
 */
@Component
public class AuthChecker {

    boolean check(Long id, User user) {
        return id != null && user != null && id.equals(user.getId());
    }

    boolean check(CheckList list, User user) {
        if(list == null) {
            return false;
        }
        if(user == null) {
            return false;
        }

        return check(list.getOwner().getId(), user);
    }
}
