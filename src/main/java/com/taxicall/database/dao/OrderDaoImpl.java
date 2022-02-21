package com.taxicall.database.dao;

import com.taxicall.database.ConnectionPool;
import com.taxicall.database.dao.interfaces.IOrderDAO;
import com.taxicall.database.entities.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements IOrderDAO {
    String columnId = "id";
    String columnClientId = "client_id";
    String columnOriginAddress = "origin_address";
    String columnDestinationAddress = "destination_address";
    String columnDistance = "distance";
    String columnNumberOfPeople = "number_of_people";
    String columnCarTypeId = "car_type_id";
    String columnCreationDate = "creation_date";
    String columnClientComment = "client_comment";
    String columnClientGrade = "client_grade";
    String columnDriverId = "driver_id";
    String columnWaitingTime = "waiting_time";
    String columnDriverComment = "driver_comment";
    String columnDriverGrade = "driver_grade";
    String columnCarId = "car_id";
    String columnDispatcherId = "dispatcher_id";
    String columnApproved = "approved";
    String columnOrderStatus = "order_status";
    String columnTotalPayment = "total_payment";

    public Connection connection = null;
    public Statement statement = null;

    public OrderDaoImpl() {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            connection = connectionPool.getConnection("Order Data Source");
            statement = connection.createStatement();
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private Order getOrderObj(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(columnId);
        long clientID = resultSet.getLong(columnClientId);
        String origin = resultSet.getString(columnOriginAddress);
        String destination = resultSet.getString(columnDestinationAddress);
        int distance = resultSet.getInt(columnDistance);
        int people = resultSet.getInt(columnNumberOfPeople);
        long carTypeID = resultSet.getLong(columnCarTypeId);
        String creationDate = resultSet.getString(columnCreationDate);
        String clientComment = resultSet.getString(columnClientComment);
        int clientGrade = resultSet.getInt(columnClientGrade);
        long driverID = resultSet.getLong(columnDriverId);
        String waitingTime = resultSet.getString(columnWaitingTime);
        String driverComment = resultSet.getString(columnDriverComment);
        int driverGrade = resultSet.getInt(columnDriverGrade);
        long carID = resultSet.getLong(columnCarId);
        long dispatcherID = resultSet.getLong(columnDispatcherId);
        boolean approved = resultSet.getBoolean(columnApproved);
        String orderStatus = resultSet.getString(columnOrderStatus);
        double totalPayment = resultSet.getDouble(columnTotalPayment);

        return new Order(id, clientID, origin, destination, distance, people, carTypeID, creationDate, clientComment, clientGrade, driverID, waitingTime, driverComment, driverGrade, carID, dispatcherID, approved, orderStatus, totalPayment);
    }

    public List<Order> findAll() {
        String query = "select * from orders;";
        List<Order> orders = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "origin_address" + "\t\t" + "destination_address");

            while (resultSet.next()) {
                Order order = getOrderObj(resultSet);
                orders.add(order);
                System.out.println(order.getId() + "\t\t" + order.getOriginAddress() + "\t\t" + order.getDestinationAddress());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return orders;
    }

    public Order findByID(long orderID) {
        String query = "select * from orders where id=" + orderID;
        Order order = null;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                order = getOrderObj(resultSet);
                System.out.println("id" + "\t\t" + "origin_address" + "\t\t" + "destination_address");
                System.out.println(order.getId() + "\t\t" + order.getOriginAddress() + "\t\t" + order.getDestinationAddress());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return order;
    }

    public List<Order> findByDriverID(long driverID) {
        String query = "select * from orders where driver_id=" + driverID;
        List<Order> orders = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Order order = getOrderObj(resultSet);
                orders.add(order);
                System.out.println("id" + "\t\t" + "origin_address" + "\t\t" + "destination_address");
                System.out.println(order.getId() + "\t\t" + order.getOriginAddress() + "\t\t" + order.getDestinationAddress());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return orders;
    }

    public List<Order> findByClientID(long clientID) {
        String query = "select * from orders where client_id=" + clientID;
        List<Order> orders = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Order order = getOrderObj(resultSet);
                orders.add(order);
                System.out.println("id" + "\t\t" + "origin_address" + "\t\t" + "destination_address");
                System.out.println(order.getId() + "\t\t" + order.getOriginAddress() + "\t\t" + order.getDestinationAddress());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return orders;
    }

    public List<Order> findByDispatcherID(long dispatcherID) {
        String query = "select * from orders where dispatcher_id=" + dispatcherID;
        List<Order> orders = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Order order = getOrderObj(resultSet);
                orders.add(order);
                System.out.println("id" + "\t\t" + "origin_address" + "\t\t" + "destination_address");
                System.out.println(order.getId() + "\t\t" + order.getOriginAddress() + "\t\t" + order.getDestinationAddress());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return orders;
    }

    public long save(OrderCLient clientInfo) {
        long newOrderID = 0;
        String query = "call create_order(null, " + clientInfo.getClientID() + ",'" + clientInfo.getOriginAddress() + "','" + clientInfo.getDestinationAddress() + "'," + clientInfo.getDistance() + "," + clientInfo.getNumberOfPeople() + "," + clientInfo.getCarTypeId() + ")";

        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                long ind = resultSet.getLong("ind");
                newOrderID = ind;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return newOrderID;
    }

    public void updateByDispatcher(OrderDispatcher dispatcherInfo) {
        String query = "call update_order_dispatcher(" + dispatcherInfo.getID() + "," + dispatcherInfo.getDispatcherID() + "," + dispatcherInfo.isApproved() + ",'" + dispatcherInfo.getTotalPayment() + "','" + dispatcherInfo.orderStatus() + "');";

        try {
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void updateByDriver(OrderDriver driverInfo) {
        String query = "call take_order_driver(" + driverInfo.getID() + "," + driverInfo.getDriverID() + ",'" + driverInfo.getWaitingTime() + "','" + driverInfo.getOrderStatus() + "'," + driverInfo.getCarId() + ");";

        try {
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void updateFeedback(OrderFeedback feedback, String role) {
        String query = "call grade_order_" + role + "(" + feedback.getID() + ",'" + feedback.getComment() + "'," + feedback.getGrade() + ");";

        try {
            statement.execute(query);
        } catch (SQLException error) {
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
