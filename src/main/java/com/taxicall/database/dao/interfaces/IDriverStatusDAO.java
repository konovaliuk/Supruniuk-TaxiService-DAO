package com.taxicall.database.dao.interfaces;

import com.taxicall.database.entities.DriverStatus;
import com.taxicall.database.entities.Role;

import java.util.List;

public interface IDriverStatusDAO {
    List<DriverStatus> findAll();
    DriverStatus findByDriverID(long id);
    void save(long driverID, String status);
    void update(long driverID, String status);
    void delete(long id);
}
