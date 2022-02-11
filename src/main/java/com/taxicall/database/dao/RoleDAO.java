package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.entities.Role;
import com.taxicall.database.entities.User;
import com.taxicall.database.entities.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    private final String COLUMN_ID = "id";
    private final String COLUMN_ROLENAME = "rolename";

    public List<Role> findAll() {
        String query = "select * from roles;";

        Statement statement = null;
        ResultSet resultSet = null;
        List<Role> roles = new ArrayList<>();

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "rolename");

            while (resultSet.next()) {
                long id = resultSet.getLong(COLUMN_ID);
                String rolename = resultSet.getString(COLUMN_ROLENAME);

                Role role = new Role(id, rolename);
                roles.add(role);
                System.out.println(id + "\t\t" + rolename);
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return roles;
    }

    public Role findById(long roleID) {
        String query = "select * from roles where id=" + roleID;

        Statement statement = null;
        ResultSet resultSet = null;
        Role role = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                long id = resultSet.getLong(COLUMN_ID);
                String rolename = resultSet.getString(COLUMN_ROLENAME);

                role = new Role(id, rolename);

                System.out.println(id + "\t\t" + rolename);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return role;
    }

    public long findByRolename(String role_name){
        String query = "select * from roles where rolename="+"'"+role_name+"'";

        Statement statement = null;
        ResultSet resultSet = null;
        Role role = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "rolename");

            while (resultSet.next()) {
                long id = resultSet.getLong(COLUMN_ID);
                String rolename = resultSet.getString(COLUMN_ROLENAME);

                role = new Role(id, rolename);
                System.out.println(id + "\t\t" + rolename);
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return role.getId();
    }

    public void save(String rolename){
        String query = "call create_role('"+rolename+"')";

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

    public void update(long id, String rolename) {
        String query = "call update_role(" + id + "," + "'" + rolename + "');";

        Statement statement = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
        public void delete(long id) {
            String query = "call delete_role("+id+")";

            Statement statement = null;

            try {
                Connection connection = Main.connect();
                statement = connection.createStatement();
                statement.execute(query);
            } catch (Exception error) {
                error.printStackTrace();
            }
    }
//    void update(long id, String rolename);
//    void delete(long id);
}
