package com.taxicall.database;

import com.taxicall.database.dao.*;
import com.taxicall.database.entities.Car;
import com.taxicall.database.entities.Role;
import com.taxicall.database.entities.User;

public class Main {
    static ConnectionPool connectionPool;

    public void connect() {
        connectionPool = new ConnectionPool();
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.connect();

       UsersDaoImpl usersDAO = new UsersDaoImpl();
       User user = new User(0, "q", "q","q@example.com", "q", null);
       usersDAO.save(user, 3);
        usersDAO.findAll();
    System.out.println("------------------------------------");
        RoleDaoImpl roleDao = new RoleDaoImpl();
        roleDao.save("q");
        roleDao.findAll();


        try {
            usersDAO.closeConnection();
            roleDao.closeConnection();
            System.out.println("Connection closed successfully.");
        } catch (Error e) {
            System.err.println("Connection close error");
        }
    }
}

//    private void connect() {
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.createStatement();
//            System.out.println("Connected to the PostgreSQL server successfully.");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public static Connection connection = null;
//
//    private void connect() {
//        try {
//            ConnectionPool connectionPool = ConnectionPool.getInstance();
//            connection = connectionPool.getConnection("data");
//            System.out.println("Connected to the PostgreSQL server successfully.");
//        } catch (Error e){
//            System.out.println("error");
//        }
//    }
