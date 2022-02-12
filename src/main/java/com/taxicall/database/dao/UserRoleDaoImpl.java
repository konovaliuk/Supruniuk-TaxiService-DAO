package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.dao.interfaces.IUserRoleDAO;
import com.taxicall.database.entities.User;
import com.taxicall.database.entities.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements IUserRoleDAO {
    private final String COLUMN_ID = "id";
    private final String COLUMN_USER_ID = "user_id";
    private final String COLUMN_ROLE_ID = "role_id";

    public List<UserRole> findAll() {
        String query = "select * from user_role;";

        Statement statement = null;
        ResultSet resultSet = null;
        List<UserRole> userRoleList = new ArrayList<>();

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "userID" + "\t\t" + "roleID");

            while (resultSet.next()) {
                long id = resultSet.getLong(COLUMN_ID);
                long userID = resultSet.getLong(COLUMN_USER_ID);
                long roleID = resultSet.getLong(COLUMN_ROLE_ID);

                UserRole userRole = new UserRole(id, userID, roleID);
                userRoleList.add(userRole);
                //System.out.println("name" + user.getName());
                System.out.println(id + "\t\t" + userID + "\t\t" + roleID);
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return userRoleList;
    }

    public UserRole findConnectionById(long connection_id) {
        String query = "select * from user_role where id=" + connection_id;

        Statement statement = null;
        ResultSet resultSet = null;
        UserRole userRole = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                long id = resultSet.getLong(COLUMN_ID);
                long userID = resultSet.getLong(COLUMN_USER_ID);
                long roleID = resultSet.getLong(COLUMN_ROLE_ID);

                userRole = new UserRole(id, userID, roleID);

                System.out.println(id + "\t\t" + userID + "\t\t" + roleID);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return userRole;
    }


    public List<UserRole> findUserRoles(long user_id) {
        String query = "select * from user_role where user_id=" + user_id;
        List<UserRole> userRoleList = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;
        UserRole userRole = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                long id = resultSet.getLong(COLUMN_ID);
                long userID = resultSet.getLong(COLUMN_USER_ID);
                long roleID = resultSet.getLong(COLUMN_ROLE_ID);

                userRole = new UserRole(id, userID, roleID);
                userRoleList.add(userRole);
                System.out.println(id + "\t\t" + userID + "\t\t" + roleID);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return userRoleList;
    }

    public void save(long user_id, long role_id) {
        User newUser = null;
        String query = "call connect_user_role("+user_id+","+role_id+")";

        Statement statement = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            statement.execute(query);
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_user_role_connection("+id+")";

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
