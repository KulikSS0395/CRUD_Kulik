package com.example.crud_kulik.controller;

import com.example.crud_kulik.entity.Role;
import com.example.crud_kulik.entity.User;
import com.example.crud_kulik.service.RoleService;
import com.example.crud_kulik.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("admin")
@Log
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.allRoles());
        return "new";
    }

    @PostMapping("/")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(required = false, name = "1") String ADMIN,
                             @RequestParam(required = false, name = "2") String USER) {
        Set<Role> roleSet = new HashSet<>();
        if (ADMIN != null) {
            roleSet.add(roleService.getRoleById(1));
        }
        if (USER != null) {
            roleSet.add(roleService.getRoleById(2));
        }
        if (USER == null & ADMIN == null) {
            roleSet.add(roleService.getRoleById(2));
        }
        user.setRoles(roleSet);
        userService.addUser(user);

        return "redirect:";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.allRoles());
        return "edit";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(required = false, name = "1") String ADMIN,
                             @RequestParam(required = false, name = "2") String USER) {
        Set<Role> roleSet = new HashSet<>();
        if (ADMIN != null) {
            roleSet.add(roleService.getRoleById(1));
        }
        if (USER != null) {
            roleSet.add(roleService.getRoleById(2));
        }
        if (USER == null & ADMIN == null) {
            roleSet.add(roleService.getRoleById(2));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:";
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:";
    }

    @GetMapping("/user_{name}")
    public String showUser(@PathVariable("name") String name, Model model) {
        model.addAttribute("user", userService.findByFirstName(name));
        return "user";
    }

}
