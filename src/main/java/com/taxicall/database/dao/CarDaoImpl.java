package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.dao.interfaces.ICarDAO;
import com.taxicall.database.entities.Car;
import com.taxicall.database.entities.CarType;
import com.taxicall.database.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements ICarDAO {
    private final String COLUMN_ID = "id";
    private final String COLUMN_DRIVER_ID = "driver_id";
    private final String COLUMN_LISENCE_NUMBER = "license_number";
    private final String COLUMN_MODEL = "model";
    private final String COLUMN_COLOR = "color";
    private final String COLUMN_TYPE_ID = "type_id";
    private final String COLUMN_CREATION_DATE = "creation_date";

    public List<Car> findAll(){
        String query = "select * from cars;";

        Statement statement = null;
        ResultSet resultSet = null;
        List<Car> cars = new ArrayList<>();

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "driver_id"
                    +  "\t\t" + "license_number" + "\t\t" + "model"
                    + "\t\t" + "color" + "\t\t" + "type_id" + "\t\t" + "creation_date");

            while (resultSet.next()) {
                long id = resultSet.getLong(COLUMN_ID);
                long driver_id = resultSet.getLong(COLUMN_DRIVER_ID);
                String license_number = resultSet.getString(COLUMN_LISENCE_NUMBER);
                String model = resultSet.getString(COLUMN_MODEL);
                String color = resultSet.getString(COLUMN_COLOR);
                long type_id = resultSet.getLong(COLUMN_TYPE_ID);
                String creation_date = resultSet.getString(COLUMN_CREATION_DATE);

                Car car= new Car(id, driver_id, license_number, model,
                        color, type_id, creation_date);
                cars.add(car);
                System.out.println(id + "\t\t" + driver_id
                        +  "\t\t\t" + license_number + "\t\t\t" + model
                        + "\t\t\t" + color + "\t\t\t" + type_id + "\t\t\t" + creation_date);
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return cars;
    }

    public Car findByID(long carID) {
        String query = "select * from cars where id=" + carID;

        Statement statement = null;
        ResultSet resultSet = null;
        Car car = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                long id = resultSet.getLong(COLUMN_ID);
                long driver_id = resultSet.getLong(COLUMN_DRIVER_ID);
                String license_number = resultSet.getString(COLUMN_LISENCE_NUMBER);
                String model = resultSet.getString(COLUMN_MODEL);
                String color = resultSet.getString(COLUMN_COLOR);
                long type_id = resultSet.getLong(COLUMN_TYPE_ID);
                String creation_date = resultSet.getString(COLUMN_CREATION_DATE);

                car= new Car(id, driver_id, license_number, model,
                        color, type_id, creation_date);

                System.out.println(id + "\t\t" + driver_id
                        +  "\t\t\t" + license_number + "\t\t\t" + model
                        + "\t\t\t" + color + "\t\t\t" + type_id + "\t\t\t" + creation_date);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return car;
    }

    public List<Car> findByDriverID(long driverID){
        String query = "select * from cars where driver_id=" + driverID;

        Statement statement = null;
        ResultSet resultSet = null;
        List<Car> cars = new ArrayList<>();

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "driver_id"
                    +  "\t\t" + "license_number" + "\t\t" + "model"
                    + "\t\t" + "color" + "\t\t" + "type_id" + "\t\t" + "creation_date");

            while (resultSet.next()) {
                long id = resultSet.getLong(COLUMN_ID);
                long driver_id = resultSet.getLong(COLUMN_DRIVER_ID);
                String license_number = resultSet.getString(COLUMN_LISENCE_NUMBER);
                String model = resultSet.getString(COLUMN_MODEL);
                String color = resultSet.getString(COLUMN_COLOR);
                long type_id = resultSet.getLong(COLUMN_TYPE_ID);
                String creation_date = resultSet.getString(COLUMN_CREATION_DATE);

                Car car= new Car(id, driver_id, license_number, model,
                        color, type_id, creation_date);
                cars.add(car);
                System.out.println(id + "\t\t" + driver_id
                        +  "\t\t\t" + license_number + "\t\t\t" + model
                        + "\t\t\t" + color + "\t\t\t" + type_id + "\t\t\t" + creation_date);
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return cars;
    }

    public long save(Car car) {
        long newCarID = 0;
        String query = "call create_car(null ,'"
                +car.getDriverID()+"','"
                +car.getLicenseNumber()+"','"
                +car.getModel()+"','"
                +car.getColor()+"',"
                + car.getTypeID()+")";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long ind = resultSet.getLong("ind");
                newCarID = ind;
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return newCarID;
    }

    public void update(long carID, String field, String value){
        String query = "call update_car_"+field+"("+carID+","+"'"+value+"');";

        Statement statement = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            statement.execute(query);
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void update(long carID, String field, long value){
        String query = "call update_car_"+field+"("+carID+","+value+");";

        Statement statement = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            statement.execute(query);
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long carID) {
        String query = "call delete_car("+carID+")";

        Statement statement = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
