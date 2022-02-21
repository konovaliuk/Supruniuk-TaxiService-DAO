package com.taxicall.database;

import com.taxicall.database.dao.*;

public class Main {

    public static void main(String[] args) {
//        Main app = new Main();

        UsersDaoImpl usersDAO = new UsersDaoImpl();
//        User user = new User(0, "Shelly",
//                "Wilson","shelly.wilson@example.com",
//                "ziggy1", null);
//        usersDAO.save(user, 3);
        usersDAO.findAll();
//   ---------------------- USER ACTIONS -----------------------------

//        System.out.println(newUser.getFullName());


//        User newUser = usersDAO.save(user, 1);
//        System.out.println(newUser.getFullName());

//        System.out.println("before");
//        usersDAO.findAll();
//        usersDAO.delete(18);
//        System.out.println("after");
//        usersDAO.findAll();

//       User newUser = usersDAO.update(5, "name", "Yuliia");
//       System.out.println(newUser.getFullName());


//  ----------------  USER ROLE ---------------------

        UserRoleDaoImpl userRoleDAO = new UserRoleDaoImpl();

        userRoleDAO.findAll();
//        System.out.println(userRoleDAO.findUserRoles(5));
//        userRoleDAO.save(5, 3);
//        userRoleDAO.delete(13);


//  ----------------  ROLE ---------------------

//        RoleDaoImpl roleDAO = new RoleDaoImpl();
//
//        roleDAO.findAll();
//        roleDAO.findById(1);
//        System.out.println(roleDAO.findByRolename("admin"));
//        roleDAO.save("test2");
//        roleDAO.update(1, "admin");
//        roleDAO.delete(8);
//
//  ----------------  DRIVER STATUS  ---------------------
//
//        DriverStatusDaoImpl driverStatusDAO = new DriverStatusDaoImpl();
//
//      driverStatusDAO.findAll();
//        driverStatusDAO.findByDriverID(7);
//        driverStatusDAO.delete(7);
//        driverStatusDAO.save(7, "busy");
//        driverStatusDAO.update(7, "available");

//  ----------------  CAR TYPE  ---------------------

//        CarTypeDaoImpl carTypeDAO = new CarTypeDaoImpl();
//
//        carTypeDAO.findAll();
//          carTypeDAO.findByID(1);
//        carTypeDAO.save("minibus", "LLAKSL");
//        carTypeDAO.update(4, "minibus", "new descr");
//        carTypeDAO.delete(4);
//        carTypeDAO.findByTypename("comfort");


//  ----------------  CARS  ---------------------

//        CarDaoImpl carDao = new CarDaoImpl();
//
//        carDao.findAll();
//        carDao.findByID(3);
//        carDao.findByDriverID(6);
//        Car car = new Car(0, 7, "5t67f3",
//                "super", "black",2, "");
//        System.out.println(carDao.save(car));
//        carDao.update(3, "type_id", 1);
//        carDao.update(3, "color", "pink");
//        carDao.delete(5);

//  ----------------  ORDERS  ---------------------

//        OrderDaoImpl orderDao = new OrderDaoImpl();
//        orderDao.findAll();
//        orderDao.findByID(2);

//        OrderCLient orderCLient = new OrderCLient(6, "2306 Chapmans Lane",
//                "3449 Boggess Street", 67, 1, 1);
//        System.out.println("id   " + orderDao.save(orderCLient));

        //bug with money type
//        OrderDispatcher orderDispatcher = new OrderDispatcher(1,7, true, "completed", 347.6);
//        orderDao.updateByDispatcher(orderDispatcher);

//        OrderDriver orderDriver = new OrderDriver(2,6, "8min", "completed", 3 );
//        orderDao.updateByDriver(orderDriver);
//
//        OrderFeedback feedback =new OrderFeedback(2, "super class", 5);
//        orderDao.updateFeedback(feedback, "client");
//        orderDao.updateFeedback(feedback, "driver");

        try {
            usersDAO.closeConnection();
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
