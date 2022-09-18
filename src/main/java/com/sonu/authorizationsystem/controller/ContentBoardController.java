package com.sonu.authorizationsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/*
Rest controller to test role based access
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/content-board")
@Slf4j
public class ContentBoardController {
    @GetMapping("/all")
    public String allAccess() {
        log.info("Public access");
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Admin only access");
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Admin only access");
        return "Admin Content";
    }
}
