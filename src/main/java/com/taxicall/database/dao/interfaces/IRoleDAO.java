package com.taxicall.database.dao.interfaces;

import com.taxicall.database.entities.Role;

import java.util.List;

public interface IRoleDAO {
    List<Role> findAll();
    Role findById(long id);
    long findByRolename(String rolename);
    void save(String rolename);
    void update(long id, String rolename);
    void delete(long id);
}
