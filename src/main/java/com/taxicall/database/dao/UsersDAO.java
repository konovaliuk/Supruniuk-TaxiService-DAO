package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.dao.interfaces.IUsersDAO;
import com.taxicall.database.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO implements IUsersDAO {
    private String COLUMN_ID = "id";
    private String COLUMN_NAME = "name";
    private String COLUMN_SURNAME = "surname";
    private String COLUMN_EMAIL = "email";
    private String COLUMN_PASSWORD = "password_hash";
    private String COLUMN_CREATION_DATE = "created_on";

    public List<User> findAll () {
        String query = "select * from users";

        Statement statement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<User>();

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long id = resultSet.getLong(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);
                String surname = resultSet.getString(COLUMN_SURNAME);
                String email = resultSet.getString(COLUMN_EMAIL);
                String password = resultSet.getString(COLUMN_PASSWORD);
                String creation_date = resultSet.getString(COLUMN_CREATION_DATE);

                User user = new User(id, name, surname, email, password, creation_date);
                userList.add(user);
                //System.out.println("name" + user.getName());
                System.out.println(id + "\t\t" + name + "\t\t" + surname + "\t\t" + email + "\t\t" + password + "\t\t" + creation_date);
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return userList;
    }

    public User findById(long user_id) {
        String query = "select * from users where id=" + user_id;

        Statement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                long id = resultSet.getLong(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);
                String surname = resultSet.getString(COLUMN_SURNAME);
                String email = resultSet.getString(COLUMN_EMAIL);
                String password = resultSet.getString(COLUMN_PASSWORD);
                String creation_date = resultSet.getString(COLUMN_CREATION_DATE);

                user = new User(id, name, surname, email, password, creation_date);
                //System.out.println("name" + user.getName());
                System.out.println(id+"\t\t"+name+"\t\t"+surname+"\t\t"+email+"\t\t"+password+"\t\t"+creation_date);
            }
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

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long ind = resultSet.getLong("ind");
                newUser = findById(ind);
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return newUser;
    }

    public User update(long ID, String field, String value) {
        User newUser = null;
        String query = "call update_user_"+field+"("+ID+","+"'"+value+"');";

        Statement statement = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            statement.execute(query);

            newUser = findById(ID);
        }
        catch (SQLException error) {
            error.printStackTrace();
        }

        return newUser;
    }

    public void delete(long ind) {
        String query = "call delete_user("+ind+")";

        Statement statement = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

}
