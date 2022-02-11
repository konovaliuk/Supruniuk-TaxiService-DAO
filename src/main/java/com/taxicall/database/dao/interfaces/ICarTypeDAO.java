package com.taxicall.database.dao.interfaces;

import com.taxicall.database.entities.CarType;

import java.util.List;

public interface ICarTypeDAO {
    List<CarType> findAll();
    CarType findByID(long id);
    CarType findByTypename(String typename);
    void save(String typename, String description);
    void update(long id, String typename, String description);
    void delete(long id);
}
