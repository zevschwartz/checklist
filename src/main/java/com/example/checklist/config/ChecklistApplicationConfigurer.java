package com.example.checklist.config;

import com.example.checklist.db.auditing.SpringSecurityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * Created by czrif on 11/15/2016.
 */
@Configuration
public class ChecklistApplicationConfigurer {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new SpringSecurityAuditorAware();
    }

}
