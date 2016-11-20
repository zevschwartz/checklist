package com.example.rest.config;

import com.example.rest.user.User;
import com.example.rest.security.SpringSecurityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by czrif on 11/15/2016.
 */
@Configuration
@EnableJpaAuditing
public class ChecklistApplicationConfigurer {

    @Bean
    public AuditorAware<User> auditorProvider() {
        return new SpringSecurityAuditorAware();
    }

}
