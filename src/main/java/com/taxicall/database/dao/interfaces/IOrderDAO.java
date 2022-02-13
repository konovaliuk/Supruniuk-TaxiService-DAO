package com.taxicall.database.dao.interfaces;

import com.taxicall.database.entities.*;

import java.util.List;

public interface IOrderDAO {
    List<Order> findAll();
    Order findByID(long id);
    List<Order> findByDriverID(long driverID);
    List<Order> findByClientID(long clientID);
    List<Order> findByDispatcherID(long dispatcherID);
    long save(OrderCLient clientInfo);
    void updateByDispatcher(OrderDispatcher dispatcherInfo);
    void updateByDriver(OrderDriver driverInfo);
    void updateFeedback(OrderFeedback feedback, String role);
}
