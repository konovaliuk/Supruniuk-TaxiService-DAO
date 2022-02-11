package com.taxicall.database.dao.interfaces;

import com.taxicall.database.entities.User;

import java.util.List;

public interface IUsersDAO {
    List<User> findAll();
    User findById(long id);
    User save(User user, long roleID);
    User update(long id, String field, String value);
    void delete(long id);
}
