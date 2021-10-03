package com.example.crud_kulik.controller;

import com.example.crud_kulik.entity.User;
import com.example.crud_kulik.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("admin")
@Log
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "users";
    }

    @GetMapping("users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:users";
    }

    @GetMapping("users/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PutMapping("users/{id}")
    public String updateUser(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:users";
    }

    @DeleteMapping("users/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:users";
    }

    @GetMapping("/user_{name}")
    public String showUser(@PathVariable("name") String name, Model model) {
        model.addAttribute("user", userService.findByFirstName(name));
        return "user";
    }

}
