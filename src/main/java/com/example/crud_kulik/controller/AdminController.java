package com.example.crud_kulik.controller;

import com.example.crud_kulik.entity.Role;
import com.example.crud_kulik.entity.User;
import com.example.crud_kulik.service.RoleService;
import com.example.crud_kulik.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String allUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("logUser", user);
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("updateUser", new User());
        model.addAttribute("roles", roleService.allRoles());
        return "admin";
    }

    @PostMapping("/")
    public String createUser(@ModelAttribute("newUser") User user,
                             @RequestParam(required = false, name = "roles[]") String[] ROLES) {
        Set<Role> roleSet = new HashSet<>();
        if (ROLES == null) {
            roleSet.add(roleService.getRoleById(2L));
        } else {
            for (String role : ROLES) {
                roleSet.add(roleService.getRoleById(Integer.parseInt(role)));
            }
        }
        user.setRoles(roleSet);
        userService.addUser(user);

        return "redirect:";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateUser(@RequestParam(required = false, name = "firstNameEdit") String firstNameEdit,
                             @RequestParam(required = false, name = "lastNameEdit") String lastNameEdit,
                             @RequestParam(required = false, name = "ageEdit") int ageEdit,
                             @RequestParam(required = false, name = "emailEdit") String emailEdit,
                             @RequestParam(required = false, name = "passwordEdit") String passwordEdit,
                             @RequestParam(required = false, name = "roles[]") String[] ROLES,
                             @RequestParam (name = "idEdit") long idEdit) {
        User updateUser = userService.getUserById(idEdit);
        updateUser.setFirstName(firstNameEdit);
        updateUser.setLastName(lastNameEdit);
        updateUser.setAge(ageEdit);
        updateUser.setEmail(emailEdit);
        updateUser.setPassword(passwordEdit);
        Set<Role> roleSet = new HashSet<>();
        if (ROLES == null) {
            roleSet.add(roleService.getRoleById(2L));
        } else {
            for (String role : ROLES) {
                roleSet.add(roleService.getRoleById(Integer.parseInt(role)));
            }
        }
        updateUser.setRoles(roleSet);
        userService.updateUser(updateUser);
        return "redirect:";
    }

    @DeleteMapping("/")
    public String removeUser(@RequestParam (name = "idDelete") long idDelete) {
        userService.removeUser(idDelete);
        return "redirect:";
    }

}
