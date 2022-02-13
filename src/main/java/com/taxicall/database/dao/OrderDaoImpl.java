package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl {
    private final String COLUMN_ID = "id";
    private final String COLUMN_CLIENT_ID = "client_id";
    private final String COLUMN_ORIGIN_ADDRESS = "origin_address";
    private final String COLUMN_DESTINATION_ADDRESS = "destination_address";
    private final String COLUMN_DISTANCE = "distance";
    private final String COLUMN_NUMBER_OF_PEOPLE = "number_of_people";
    private final String COLUMN_CAR_TYPE_ID = "car_type_id";
    private final String COLUMN_CREATION_DATE = "creation_date";
    private final String COLUMN_CLIENT_COMMENT = "client_comment";
    private final String COLUMN_CLIENT_GRADE = "client_grade";
    private final String COLUMN_DRIVER_ID = "driver_id";
    private final String COLUMN_WAITING_TIME = "waiting_time";
    private final String COLUMN_DRIVER_COMMENT = "driver_comment";
    private final String COLUMN_DRIVER_GRADE = "driver_grade";
    private final String COLUMN_CAR_ID= "car_id";
    private final String COLUMN_DISPATCHER_ID = "dispatcher_id";
    private final String COLUMN_APPROVED = "approved";
    private final String COLUMN_ORDER_STATUS = "order_status";
    private final String COLUMN_TOTAL_PAYMENT= "total_payment";


    private Order getOrderObj(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        long clientID = resultSet.getLong(COLUMN_CLIENT_ID);
        String origin = resultSet.getString(COLUMN_ORIGIN_ADDRESS);
        String destination = resultSet.getString(COLUMN_DESTINATION_ADDRESS);
        int distance = resultSet.getInt(COLUMN_DISTANCE);
        int people = resultSet.getInt(COLUMN_NUMBER_OF_PEOPLE);
        long carTypeID = resultSet.getLong(COLUMN_CAR_TYPE_ID);
        String creationDate = resultSet.getString(COLUMN_CREATION_DATE);
        String clientComment = resultSet.getString(COLUMN_CLIENT_COMMENT);
        int clientGrade = resultSet.getInt(COLUMN_CLIENT_GRADE);
        long driverID = resultSet.getLong(COLUMN_DRIVER_ID);
        String waitingTime = resultSet.getString(COLUMN_WAITING_TIME);
        String driverComment = resultSet.getString(COLUMN_DRIVER_COMMENT);
        int driverGrade = resultSet.getInt(COLUMN_DRIVER_GRADE);
        long carID = resultSet.getLong(COLUMN_CAR_ID);
        long dispatcherID = resultSet.getLong(COLUMN_DISPATCHER_ID);
        boolean approved = resultSet.getBoolean(COLUMN_APPROVED);
        String orderStatus = resultSet.getString(COLUMN_ORDER_STATUS);
        double totalPayment = resultSet.getDouble(COLUMN_TOTAL_PAYMENT);

        return new Order(id, clientID, origin, destination,
                distance, people, carTypeID, creationDate,
                clientComment, clientGrade, driverID, waitingTime,
                driverComment, driverGrade, carID, dispatcherID,
                approved, orderStatus, totalPayment);
    }

    public List<Order> findAll() {
        String query = "select * from orders;";
        List<Order> orders = new ArrayList<>();

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "origin_address" + "\t\t" + "destination_address");

            while (resultSet.next()) {
                Order order = getOrderObj(resultSet);
                orders.add(order);
                System.out.println(order.getId() + "\t\t" + order.getOriginAddress() + "\t\t" + order.getDestinationAddress());
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return orders;
    }

    public Order findByID(long orderID) {
        String query = "select * from orders where id=" + orderID;
        Order order = null;

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while(resultSet.next()){
                order = getOrderObj(resultSet);
                System.out.println("id" + "\t\t" + "origin_address" + "\t\t" + "destination_address");
                System.out.println(order.getId() + "\t\t" + order.getOriginAddress()+"\t\t"+order.getDestinationAddress());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return order;
    }

    public Order findByDriverID(long driverID) {
        String query = "select * from orders where driver_id=" + driverID;
        Order order = null;

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while(resultSet.next()){
                order = getOrderObj(resultSet);
                System.out.println("id" + "\t\t" + "origin_address" + "\t\t" + "destination_address");
                System.out.println(order.getId() + "\t\t" + order.getOriginAddress()+"\t\t"+order.getDestinationAddress());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return order;
    }

    public Order findByClientID(long clientID) {
        String query = "select * from orders where client_id=" + clientID;
        Order order = null;

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while(resultSet.next()){
                order = getOrderObj(resultSet);
                System.out.println("id" + "\t\t" + "origin_address" + "\t\t" + "destination_address");
                System.out.println(order.getId() + "\t\t" + order.getOriginAddress()+"\t\t"+order.getDestinationAddress());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return order;
    }

    public Order findByDispatcherID(long dispatcherID) {
        String query = "select * from orders where dispatcher_id=" + dispatcherID;
        Order order = null;

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while(resultSet.next()){
                order = getOrderObj(resultSet);
                System.out.println("id" + "\t\t" + "origin_address" + "\t\t" + "destination_address");
                System.out.println(order.getId() + "\t\t" + order.getOriginAddress()+"\t\t"+order.getDestinationAddress());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return order;
    }
    public long save(OrderCLient clientInfo) {
        long newOrderID = 0;
        String query = "call create_order(null, " + clientInfo.getClientID() + ",'"
                +clientInfo.getOriginAddress()+"','"
                +clientInfo.getDestinationAddress()+"',"
                +clientInfo.getDistance()+","
                +clientInfo.getNumberOfPeople()+","
                +clientInfo.getCarTypeId()+")";

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while (resultSet.next()) {
                long ind = resultSet.getLong("ind");
                newOrderID = ind;
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return newOrderID;
    }

    public void updateByDispatcher(OrderDispatcher dispatcherInfo){
        String query = "call update_order_dispatcher("+dispatcherInfo.getID()
                +","+dispatcherInfo.getDispatcherID()+","+ dispatcherInfo.isApproved() + ",'" +
                dispatcherInfo.getTotalPayment() +"','"+ dispatcherInfo.orderStatus()+"');";

        try {
            Main.statement.execute(query);
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void updateByDriver(OrderDriver driverInfo){
        String query = "call take_order_driver("+driverInfo.getID()
                +","+driverInfo.getDriverID()+",'"+ driverInfo.getWaitingTime() + "','" +
                driverInfo.getOrderStatus() +"',"+ driverInfo.getCarId()+");";

        try {
            Main.statement.execute(query);
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void updateFeedback(OrderFeedback feedback, String role) {
        String query = "call grade_order_"+role+"("+feedback.getID()
                +",'"+feedback.getComment()+"',"+ feedback.getGrade() + ");";

        try {
            Main.statement.execute(query);
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

}
