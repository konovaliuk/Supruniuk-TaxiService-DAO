package com.taxicall.database.dao.interfaces;

import com.taxicall.database.entities.User;

public interface IUsersDAO {
    void findAll();
    User findById(int id);
    User save(User user, int roleID);
    void delete(int id);
}
