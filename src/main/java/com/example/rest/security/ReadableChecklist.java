package com.example.rest.security;

import org.springframework.security.access.prepost.PostAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by czrif on 11/20/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@PostAuthorize("hasRole('ADMIN') or @AuthChecker.check(returnObject, principal)")
public @interface ReadableChecklist {
}
