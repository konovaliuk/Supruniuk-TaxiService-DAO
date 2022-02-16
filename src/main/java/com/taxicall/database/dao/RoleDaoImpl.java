package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.dao.interfaces.IRoleDAO;
import com.taxicall.database.entities.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements IRoleDAO {
    private final String COLUMN_ID = "id";
    private final String COLUMN_ROLENAME = "rolename";

    private Role getRole(ResultSet resultSet)  throws SQLException  {
        long id = resultSet.getLong(COLUMN_ID);
        String rolename = resultSet.getString(COLUMN_ROLENAME);

        return new Role(id, rolename);
    }
    public List<Role> findAll() {
        String query = "select * from roles;";
        List<Role> roles = new ArrayList<>();

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);
            System.out.println("id" + "\t\t" + "rolename");
            while (resultSet.next()) {
                Role role = getRole(resultSet);
                roles.add(role);
                System.out.println(role.getId() + "\t\t" + role.getRolename());
            }

            resultSet.close();
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return roles;
    }

    public Role findById(long roleID) {
        String query = "select * from roles where id=" + roleID;
        Role role = null;

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while(resultSet.next()){
                role = getRole(resultSet);
                System.out.println(role.getId() + "\t\t" + role.getRolename());
            }

            resultSet.close();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return role;
    }

    public long findByRolename(String role_name){
        String query = "select * from roles where rolename="+"'"+role_name+"'";
        Role role = null;

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);
            System.out.println("id" + "\t\t" + "rolename");
            while (resultSet.next()) {
                role = getRole(resultSet);
                System.out.println(role.getId() + "\t\t" + role.getRolename());
            }

            resultSet.close();
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return role.getId();
    }

    public void save(String rolename){
        String query = "call create_role('"+rolename+"')";

        try {
            Main.statement.execute(query);
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void update(long id, String rolename) {
        String query = "call update_role(" + id + "," + "'" + rolename + "');";

        try {
            Main.statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_role("+id+")";

        try {
            Main.statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
