package com.example.checklist.db.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Created by czrif on 11/10/2016.
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            user = (User) authentication.getPrincipal();
        }
        return user == null ? null : user.getUsername();
    }
}
