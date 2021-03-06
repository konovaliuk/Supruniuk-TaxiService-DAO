package com.taxicall.database.dao;

import com.taxicall.database.ConnectionPool;
import com.taxicall.database.dao.dbColumns.RoleDB;
import com.taxicall.database.dao.interfaces.IRoleDAO;
import com.taxicall.database.entities.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements IRoleDAO {
    public Connection connection = null;

    public RoleDaoImpl() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection("Role Data Source");
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private Role getRole(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(RoleDB.columnId);
        String rolename = resultSet.getString(RoleDB.columnRolename);

        return new Role(id, rolename);
    }

    public List<Role> findAll() {
        String query = "select * from roles;";
        List<Role> roles = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "rolename");
            while (resultSet.next()) {
                Role role = getRole(resultSet);
                roles.add(role);
                System.out.println(role.getId() + "\t\t" + role.getRolename());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return roles;
    }

    public Role findById(long roleID) {
        String query = "select * from roles where id=" + roleID;
        Role role = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                role = getRole(resultSet);
                System.out.println(role.getId() + "\t\t" + role.getRolename());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return role;
    }

    public long findByRolename(String role_name) {
        String query = "select * from roles where rolename=" + "'" + role_name + "'";
        Role role = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "rolename");
            while (resultSet.next()) {
                role = getRole(resultSet);
                System.out.println(role.getId() + "\t\t" + role.getRolename());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return role.getId();
    }

    public void save(String rolename) {
        String query = "call create_role('" + rolename + "')";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void update(long id, String rolename) {
        String query = "call update_role(" + id + "," + "'" + rolename + "');";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_role(" + id + ")";

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
