package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.dao.interfaces.IUsersDAO;
import com.taxicall.database.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements IUsersDAO {
    private final String COLUMN_ID = "id";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_SURNAME = "surname";
    private final String COLUMN_EMAIL = "email";
    private final String COLUMN_PASSWORD = "password_hash";
    private final String COLUMN_CREATION_DATE = "created_on";

    private User getUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);
        String surname = resultSet.getString(COLUMN_SURNAME);
        String email = resultSet.getString(COLUMN_EMAIL);
        String password = resultSet.getString(COLUMN_PASSWORD);
        String creation_date = resultSet.getString(COLUMN_CREATION_DATE);

        return new User(id, name, surname, email, password, creation_date);
    }

    public List<User> findAll () {
        String query = "select * from users";
        List<User> userList = new ArrayList<User>();

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
                System.out.println(user.getId() + "\t\t" + user.getName() + "\t\t"
                        + user.getSurname() + "\t\t" + user.getSurname() + "\t\t"
                        + user.getPasswordHash() + "\t\t" + user.getCreatedOn());
            }

            resultSet.close();
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return userList;
    }

    public User findById(long user_id) {
        String query = "select * from users where id=" + user_id;

        User user = null;

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while(resultSet.next()){
                user = getUser(resultSet);
                System.out.println(user.getId() + "\t\t" + user.getName() + "\t\t"
                        + user.getSurname() + "\t\t" + user.getSurname() + "\t\t"
                        + user.getPasswordHash() + "\t\t" + user.getCreatedOn());
            }
            resultSet.close();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return user;
    }

    public User save(User user, long roleID) {
        User newUser = null;
        String query = "call create_user(null ,'"
                +user.getName()+"','"
                +user.getSurname()+"','"
                +user.getEmail()+"','"
                +user.getPasswordHash()+"',"
                +roleID+")";

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while (resultSet.next()) {
                long ind = resultSet.getLong("ind");
                newUser = findById(ind);
            }
            resultSet.close();
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return newUser;
    }

    public User update(long ID, String field, String value) {
        User newUser = null;
        String query = "call update_user_"+field+"("+ID+","+"'"+value+"');";

        try {
            Main.statement.execute(query);
            newUser = findById(ID);
        }
        catch (SQLException error) {
            error.printStackTrace();
        }

        return newUser;
    }

    public void delete(long ind) {
        String query = "call delete_user("+ind+")";

        try {
            Main.statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

}
