package com.taxicall.database.dao;

import com.taxicall.database.ConnectionPool;
import com.taxicall.database.dao.interfaces.ICarTypeDAO;
import com.taxicall.database.entities.CarType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarTypeDaoImpl implements ICarTypeDAO {
    String columnId = "id";
    String columnTypename = "typename";
    String columnDescription = "description";

    public Connection connection = null;
    public Statement statement = null;

    public CarTypeDaoImpl() {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            connection = connectionPool.getConnection("Car Type Data Source");
            statement = connection.createStatement();
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private CarType getCarType(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(columnId);
        String type = resultSet.getString(columnTypename);
        String description = resultSet.getString(columnDescription);

        return new CarType(id, type, description);
    }

    public List<CarType> findAll() {
        String query = "select * from car_types;";
        List<CarType> carTypes = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "typename" + "\t\t" + "description");

            while (resultSet.next()) {
                CarType carType = getCarType(resultSet);
                carTypes.add(carType);
                System.out.println(carType.getId() + "\t\t" + carType.getTypename() + "\t\t\t\t" + carType.getDescription());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return carTypes;
    }

    public CarType findByID(long id) {
        String query = "select * from car_types where id=" + id;
        CarType carType = null;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                carType = getCarType(resultSet);

                System.out.println("id" + "\t\t" + "typename" + "\t\t" + "description");
                System.out.println(carType.getId() + "\t\t" + carType.getTypename() + "\t\t\t\t" + carType.getDescription());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return carType;
    }

    public CarType findByTypename(String typename) {
        String query = "select * from car_types where typename='" + typename + "'";
        CarType carType = null;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                carType = getCarType(resultSet);

                System.out.println("id" + "\t\t" + "typename" + "\t\t" + "description");
                System.out.println(carType.getId() + "\t\t" + carType.getTypename() + "\t\t\t\t" + carType.getDescription());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return carType;
    }

    public void save(String typename, String description) {
        String query = "call create_type('" + typename + "','" + description + "')";

        try {
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void update(long id, String typename, String description) {
        String query = "call update_type(" + id + ",'" + typename + "','" + description + "');";

        try {
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_type(" + id + ")";

        try {
            statement.execute(query);
        } catch (Exception error) {
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
