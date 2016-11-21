package com.example.rest.security;

import com.example.rest.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by czrif on 11/10/2016.
 */
public class SpringSecurityAuditorAware implements AuditorAware<User> {
    @Override
    public User getCurrentAuditor() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            user = (User) authentication.getPrincipal();
        }
        return user == null ? new User(ThreadLocalRandom.current().nextLong(0, 3)): user;
    }
}
