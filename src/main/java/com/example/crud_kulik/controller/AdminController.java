package com.example.crud_kulik.controller;

import lombok.extern.java.Log;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("admin")
@Log
public class AdminController {

    @GetMapping("")
    public String allUsers() {
        return "admin";
    }

}
