package com.taxicall.database.dao.interfaces;

import com.taxicall.database.entities.User;

public interface IUsersDAO {
    void findAll();
    User findById(int id);
    User save(User user, int roleID);
    User update(int id, String field, String value);
    void delete(int id);
}
