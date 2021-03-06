package com.taxicall.database.dao;

import com.taxicall.database.ConnectionPool;
import com.taxicall.database.dao.dbColumns.CarDB;
import com.taxicall.database.dao.interfaces.ICarDAO;
import com.taxicall.database.entities.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements ICarDAO {
    public Connection connection = null;

    public CarDaoImpl() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection("Car Data Source");
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private Car getCar(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(CarDB.columnId);
        long driver_id = resultSet.getLong(CarDB.columnDriverId);
        String license_number = resultSet.getString(CarDB.columnLisenceNumber);
        String model = resultSet.getString(CarDB.columnModel);
        String color = resultSet.getString(CarDB.columnColor);
        long type_id = resultSet.getLong(CarDB.columnTypeId);
        String creation_date = resultSet.getString(CarDB.columnCreationDate);

        return new Car(id, driver_id, license_number, model, color, type_id, creation_date);
    }

    public List<Car> findAll() {
        String query = "select * from cars;";
        List<Car> cars = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "driver_id"
                    + "\t\t" + "license_number" + "\t\t" + "model"
                    + "\t\t" + "color" + "\t\t" + "type_id" + "\t\t" + "creation_date");

            while (resultSet.next()) {
                Car car = getCar(resultSet);
                cars.add(car);
                System.out.println(car.getId() + "\t\t" + car.getDriverID()
                        + "\t\t\t" + car.getLicenseNumber() + "\t\t\t" + car.getModel()
                        + "\t\t\t" + car.getColor() + "\t\t\t" + car.getTypeID() + "\t\t\t"
                        + car.getCreationDate());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return cars;
    }

    public Car findByID(long carID) {
        String query = "select * from cars where id=" + carID;
        Car car = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                car = getCar(resultSet);
                System.out.println(car.getId() + "\t\t" + car.getDriverID()
                        + "\t\t\t" + car.getLicenseNumber() + "\t\t\t" + car.getModel()
                        + "\t\t\t" + car.getColor() + "\t\t\t" + car.getTypeID() + "\t\t\t"
                        + car.getCreationDate());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return car;
    }

    public List<Car> findByDriverID(long driverID) {
        String query = "select * from cars where driver_id=" + driverID;
        List<Car> cars = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "driver_id"
                    + "\t\t" + "license_number" + "\t\t" + "model"
                    + "\t\t" + "color" + "\t\t" + "type_id" + "\t\t" + "creation_date");

            while (resultSet.next()) {
                Car car = getCar(resultSet);
                cars.add(car);
                System.out.println(car.getId() + "\t\t" + car.getDriverID()
                        + "\t\t\t" + car.getLicenseNumber() + "\t\t\t" + car.getModel()
                        + "\t\t\t" + car.getColor() + "\t\t\t" + car.getTypeID() + "\t\t\t"
                        + car.getCreationDate());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return cars;
    }

    public long save(Car car) {
        long newCarID = 0;
        String query = "call create_car(null ,'"
                + car.getDriverID() + "','"
                + car.getLicenseNumber() + "','"
                + car.getModel() + "','"
                + car.getColor() + "',"
                + car.getTypeID() + ")";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long ind = resultSet.getLong("ind");
                newCarID = ind;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return newCarID;
    }

    public void update(long carID, String field, String value) {
        String query = "call update_car_" + field + "(" + carID + "," + "'" + value + "');";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void update(long carID, String field, long value) {
        String query = "call update_car_" + field + "(" + carID + "," + value + ");";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long carID) {
        String query = "call delete_car(" + carID + ")";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException error) {
            System.err.println(error.getMessage());
        }
    }
}
