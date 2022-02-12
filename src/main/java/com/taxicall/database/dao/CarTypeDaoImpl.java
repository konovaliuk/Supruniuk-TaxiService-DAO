package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.dao.interfaces.ICarTypeDAO;
import com.taxicall.database.entities.CarType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarTypeDaoImpl implements ICarTypeDAO {
    private final String COLUMN_ID = "id";
    private final String COLUMN_TYPENAME = "typename";
    private final String COLUMN_DESCRIPTION = "description";

    public List<CarType> findAll() {
        String query = "select * from car_types;";

        Statement statement = null;
        ResultSet resultSet = null;
        List<CarType> carTypes = new ArrayList<>();

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "typename" +  "\t\t" + "description");

            while (resultSet.next()) {
                long id = resultSet.getLong(COLUMN_ID);
                String type = resultSet.getString(COLUMN_TYPENAME);
                String description = resultSet.getString(COLUMN_DESCRIPTION);

                CarType carType = new CarType(id, type, description);
                carTypes.add(carType);
                System.out.println(id + "\t\t" + type + "\t\t\t\t" + description);
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return carTypes;
    }

    public CarType findByID(long id) {
        String query = "select * from car_types where id=" + id;

        Statement statement = null;
        ResultSet resultSet = null;
        CarType carType = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                long ind = resultSet.getLong(COLUMN_ID);
                String type = resultSet.getString(COLUMN_TYPENAME);
                String description = resultSet.getString(COLUMN_DESCRIPTION);

                carType = new CarType(ind, type, description);

                System.out.println("id" + "\t\t" + "typename" +  "\t\t" + "description");
                System.out.println(ind + "\t\t" + type + "\t\t\t\t" + description);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return carType;
    }

    public CarType findByTypename(String typename) {
        String query = "select * from car_types where typename='" + typename+"'";

        Statement statement = null;
        ResultSet resultSet = null;
        CarType carType = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                long ind = resultSet.getLong(COLUMN_ID);
                String type = resultSet.getString(COLUMN_TYPENAME);
                String description = resultSet.getString(COLUMN_DESCRIPTION);

                carType = new CarType(ind, type, description);

                System.out.println("id" + "\t\t" + "typename" +  "\t\t" + "description");
                System.out.println(ind + "\t\t" + type + "\t\t\t\t" + description);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return carType;
    }

    public void save(String typename, String description) {
        String query = "call create_type('"+typename+"','"+description+"')";

        Statement statement = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            statement.execute(query);
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void update(long id, String typename, String description) {
        String query = "call update_type(" + id + ",'" + typename + "','"+description+"');";

        Statement statement = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_type("+id+")";

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
