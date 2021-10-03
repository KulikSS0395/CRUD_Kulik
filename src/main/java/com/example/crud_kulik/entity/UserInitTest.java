package com.example.crud_kulik.entity;

import com.example.crud_kulik.service.RoleService;
import com.example.crud_kulik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserInitTest {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserInitTest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void generateUsersAndRoleTest() {
        Role role1 = new Role(1L,"ROLE_ADMIN");
        Role role2 = new Role(2L, "ROLE_USER");
        Set<Role> allRole = new HashSet<>();
        allRole.add(role1);
        allRole.add(role2);

        User simpleUser = new User("Ivan", "Vanko", 25,
                                "vanko@mail.ru", "0000", Collections.singleton(role2));
        User adminUser = new User("admin", "admin", 33,
                                "admin@admin.ru", "admin", allRole);


        roleService.addRole(role1);
        roleService.addRole(role2);

        userService.addUser(simpleUser);
        userService.addUser(adminUser);
    }

}
