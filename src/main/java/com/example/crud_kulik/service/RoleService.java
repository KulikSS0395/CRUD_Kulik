package com.example.crud_kulik.service;


import com.example.crud_kulik.entity.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    List<Role> allRoles();
    Role getRoleById(long id);
}
