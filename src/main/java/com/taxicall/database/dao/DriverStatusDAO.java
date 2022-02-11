package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.entities.DriverStatus;
import com.taxicall.database.entities.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DriverStatusDAO {
    private final String COLUMN_DRIVER_ID = "driver_id";
    private final String COLUMN_STATUS = "status";

    public List<DriverStatus> findAll() {
        String query = "select * from driver_status;";

        Statement statement = null;
        ResultSet resultSet = null;
        List<DriverStatus> statuses = new ArrayList<>();

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("driver id" + "\t\t" + "status");

            while (resultSet.next()) {
                long id = resultSet.getLong(COLUMN_DRIVER_ID);
                String status = resultSet.getString(COLUMN_STATUS);

                DriverStatus role = new DriverStatus(id, status);
                statuses.add(role);
                System.out.println(id + "\t\t\t\t" + status);
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return statuses;
    }

    public DriverStatus findByDriverID(long driverID) {
        String query = "select * from driver_status where driver_id=" + driverID;

        Statement statement = null;
        ResultSet resultSet = null;
        DriverStatus driverStatus = null;

        try {
            Connection connection = Main.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                long id = resultSet.getLong(COLUMN_DRIVER_ID);
                String status = resultSet.getString(COLUMN_STATUS);

                driverStatus = new DriverStatus(id, status);

                System.out.println("driver id" + "\t\t" + "status");
                System.out.println(id + "\t\t\t\t" + status);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return driverStatus;
    }

    public void save(long driverID, String status) {
        String query = "call set_driver_status("+driverID+",'"+status+"')";

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

    public void update(long driverID, String status) {
        String query = "call update_driver_status(" + driverID + "," + "'" + status + "');";

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
        String query = "call delete_driver_status("+id+")";

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
