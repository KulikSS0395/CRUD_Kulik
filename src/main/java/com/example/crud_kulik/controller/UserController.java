package com.example.crud_kulik.controller;

import com.example.crud_kulik.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
@Log
public class UserController {

    @GetMapping()
    public String getUserPage() {
        return "user";
    }

}
