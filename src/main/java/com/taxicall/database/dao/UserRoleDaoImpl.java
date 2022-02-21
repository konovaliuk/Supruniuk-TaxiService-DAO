package com.taxicall.database.dao;

import com.taxicall.database.ConnectionPool;
import com.taxicall.database.dao.interfaces.IUserRoleDAO;
import com.taxicall.database.entities.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements IUserRoleDAO {
    String columnId = "id";
    String columnUserId = "user_id";
    String columnRoleId = "role_id";
    public Connection connection = null;
    public Statement statement = null;

    public UserRoleDaoImpl() {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            connection = connectionPool.getConnection("Users Role Data Source");
            statement = connection.createStatement();
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private UserRole getUserRole(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(columnId);
        long userID = resultSet.getLong(columnUserId);
        long roleID = resultSet.getLong(columnRoleId);

        return new UserRole(id, userID, roleID);
    }

    public List<UserRole> findAll() {
        String query = "select * from user_role;";
        List<UserRole> userRoleList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "userID" + "\t\t" + "roleID");

            while (resultSet.next()) {
                UserRole userRole = getUserRole(resultSet);
                userRoleList.add(userRole);
                System.out.println(userRole.getRoleID() + "\t\t" + userRole.getUserID() + "\t\t" + userRole.getRoleID());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return userRoleList;
    }

    public UserRole findConnectionById(long connection_id) {
        String query = "select * from user_role where id=" + connection_id;
        UserRole userRole = null;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                userRole = getUserRole(resultSet);
                System.out.println(userRole.getRoleID() + "\t\t" + userRole.getUserID() + "\t\t" + userRole.getRoleID());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return userRole;
    }


    public List<UserRole> findUserRoles(long user_id) {
        String query = "select * from user_role where user_id=" + user_id;
        List<UserRole> userRoleList = new ArrayList<>();
        UserRole userRole = null;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                userRole = getUserRole(resultSet);
                userRoleList.add(userRole);
                System.out.println(userRole.getRoleID() + "\t\t" + userRole.getUserID() + "\t\t" + userRole.getRoleID());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return userRoleList;
    }

    public void save(long user_id, long role_id) {
        String query = "call connect_user_role(" + user_id + "," + role_id + ")";

        try {
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_user_role_connection(" + id + ")";

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
