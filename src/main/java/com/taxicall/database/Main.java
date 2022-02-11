package com.taxicall.database;

import com.taxicall.database.dao.*;
import com.taxicall.database.entities.DriverStatus;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/taxi_service";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "timofey";

    public static Connection connect() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public static void main(String[] args) {
        Main app = new Main();
        UsersDAO usersDAO = new UsersDAO();

//   -------------- USER ACTIONS -----------------------------

//        System.out.println(usersDAO.findById(7).getFullName());

//        User user = new User(0, "Ellen",
//                "Burns","ellen.burns@example.com",
//                "1945", null);
//        User newUser = usersDAO.save(user, 1);
//        System.out.println(newUser.getFullName());

//        usersDAO.delete(8);

//       User newUser = usersDAO.update(5, "name", "Yuliia");
//       System.out.println(newUser.getFullName());



//  ----------------  USER ROLE ---------------------

        UserRoleDAO userRoleDAO = new UserRoleDAO();

//        userRoleDAO.findAll();
//        System.out.println(userRoleDAO.findUserRoles(5));
//        userRoleDAO.save(5, 3);
//        userRoleDAO.delete(13);


//  ----------------  ROLE ---------------------

        RoleDAO roleDAO = new RoleDAO();

//        roleDAO.findAll();
//        roleDAO.findById(1);
//        System.out.println(roleDAO.findByRolename("admin"));
//        roleDAO.save("test2");
//        roleDAO.update(1, "admin");
//        roleDAO.delete(8);
//
//  ----------------  DRIVER STATUS  ---------------------

        DriverStatusDAO driverStatusDAO = new DriverStatusDAO();

//      driverStatusDAO.findAll();
//        driverStatusDAO.findByDriverID(7);
//        driverStatusDAO.delete(7);
//        driverStatusDAO.save(7, "busy");
//        driverStatusDAO.update(7, "available");

//  ----------------  CAR TYPE  ---------------------

        CarTypeDAO carTypeDAO = new CarTypeDAO();

//        carTypeDAO.findAll();
//          carTypeDAO.findByID(1);
//        carTypeDAO.save("minibus", "LLAKSL");
//        carTypeDAO.update(4, "minibus", "new descr");
        carTypeDAO.delete(4);
    }
}
