package com.taxicall.database.dao.interfaces;

import com.taxicall.database.entities.Car;
import com.taxicall.database.entities.DriverStatus;
import com.taxicall.database.entities.User;

import java.util.List;

public interface ICarDAO {
    List<Car> findAll();
    Car findByID(long id);
    List<Car> findByDriverID(long driverID);
    long save(Car car);
    void update(long id, String field, String value);
    void update(long id, String field, long value);
    void delete(long id);
}
