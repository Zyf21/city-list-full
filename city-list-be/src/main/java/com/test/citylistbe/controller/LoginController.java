package com.test.citylistbe.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {

    @GetMapping
    @Operation(summary = "Get list of cities")
    public Principal login(Principal principal) {
        return principal;
    }
}
