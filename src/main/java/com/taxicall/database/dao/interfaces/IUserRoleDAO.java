package com.taxicall.database.dao.interfaces;

import com.taxicall.database.entities.User;
import com.taxicall.database.entities.UserRole;

import java.util.List;

public interface IUserRoleDAO {
    List<UserRole> findAll();
    UserRole findConnectionById(long id);
    List<UserRole> findUserRoles(long userID);
    void save(long userID, long roleID);
    void delete(long id);
}
