package com.taxicall.database.dao;

import com.taxicall.database.ConnectionPool;
import com.taxicall.database.dao.interfaces.IUsersDAO;
import com.taxicall.database.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements IUsersDAO {
    String columnId = "id";
    String columnName = "name";
    String columnSurname = "surname";
    String columnEmail = "email";
    String columnPassword = "password_hash";
    String columnCreationDate = "created_on";
    public Connection connection = null;
    public Statement statement = null;

    public UsersDaoImpl() {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            connection = connectionPool.getConnection("Users Data Source");
            statement = connection.createStatement();
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(columnId);
        String name = resultSet.getString(columnName);
        String surname = resultSet.getString(columnSurname);
        String email = resultSet.getString(columnEmail);
        String password = resultSet.getString(columnPassword);
        String creation_date = resultSet.getString(columnCreationDate);

        return new User(id, name, surname, email, password, creation_date);
    }

    public List<User> findAll() {
        String query = "select * from users";
        List<User> userList = new ArrayList<User>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
                System.out.println(user.getId() + "\t\t" + user.getName() + "\t\t"
                        + user.getSurname() + "\t\t" + user.getSurname() + "\t\t"
                        + user.getPasswordHash() + "\t\t" + user.getCreatedOn());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return userList;
    }

    public User findById(long user_id) {
        String query = "select * from users where id=" + user_id;

        User user = null;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                user = getUser(resultSet);
                System.out.println(user.getId() + "\t\t" + user.getName() + "\t\t"
                        + user.getSurname() + "\t\t" + user.getSurname() + "\t\t"
                        + user.getPasswordHash() + "\t\t" + user.getCreatedOn());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return user;
    }

    public User save(User user, long roleID) {
        User newUser = null;
        String query = "call create_user(null ,'"
                + user.getName() + "','"
                + user.getSurname() + "','"
                + user.getEmail() + "','"
                + user.getPasswordHash() + "',"
                + roleID + ")";

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long ind = resultSet.getLong("ind");
                newUser = findById(ind);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return newUser;
    }

    public User update(long ID, String field, String value) {
        User newUser = null;
        String query = "call update_user_" + field + "(" + ID + "," + "'" + value + "');";

        try {
            statement.execute(query);
            newUser = findById(ID);
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return newUser;
    }

    public void delete(long ind) {
        String query = "call delete_user(" + ind + ")";

        try {
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException error) {
            System.err.println(error.getMessage());
        }
    }
}
