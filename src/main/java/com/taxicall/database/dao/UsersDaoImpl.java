package com.taxicall.database.dao;

import com.taxicall.database.ConnectionPool;
import com.taxicall.database.dao.dbColumns.UserDB;
import com.taxicall.database.dao.interfaces.IUsersDAO;
import com.taxicall.database.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements IUsersDAO {
    public Connection connection = null;

    public UsersDaoImpl() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection("Users Data Source");
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(UserDB.columnId);
        String name = resultSet.getString(UserDB.columnName);
        String surname = resultSet.getString(UserDB.columnSurname);
        String email = resultSet.getString(UserDB.columnEmail);
        String password = resultSet.getString(UserDB.columnPassword);
        String creation_date = resultSet.getString(UserDB.columnCreationDate);

        return new User(id, name, surname, email, password, creation_date);
    }

    public List<User> findAll() {
        String query = "select * from users";
        List<User> userList = new ArrayList<User>();

        try {
            Statement statement = connection.createStatement();
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
            Statement statement = connection.createStatement();
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
            Statement statement = connection.createStatement();
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
            Statement statement = connection.createStatement();
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
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException error) {
            System.err.println(error.getMessage());
        }
    }
}
