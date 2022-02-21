package com.taxicall.database.dao;

import com.taxicall.database.ConnectionPool;
import com.taxicall.database.dao.interfaces.IDriverStatusDAO;
import com.taxicall.database.entities.DriverStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DriverStatusDaoImpl implements IDriverStatusDAO {
    String columnDriverId = "driver_id";
    String columnStatus = "status";
    public Connection connection = null;
    public Statement statement = null;

    public DriverStatusDaoImpl() {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            connection = connectionPool.getConnection("Driver Status Data Source");
            statement = connection.createStatement();
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private DriverStatus getDriverStatus(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(columnDriverId);
        String status = resultSet.getString(columnStatus);

        return new DriverStatus(id, status);
    }

    public List<DriverStatus> findAll() {
        String query = "select * from driver_status;";
        List<DriverStatus> statuses = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("driver id" + "\t\t" + "status");
            while (resultSet.next()) {
                DriverStatus driverStatus = getDriverStatus(resultSet);
                statuses.add(driverStatus);
                System.out.println(driverStatus.getId() + "\t\t\t\t" + driverStatus.getDriverStatus());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return statuses;
    }

    public DriverStatus findByDriverID(long driverID) {
        String query = "select * from driver_status where driver_id=" + driverID;
        DriverStatus driverStatus = null;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                driverStatus = getDriverStatus(resultSet);
                System.out.println("driver id" + "\t\t" + "status");
                System.out.println(driverStatus.getId() + "\t\t\t\t" + driverStatus.getDriverStatus());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return driverStatus;
    }

    public void save(long driverID, String status) {
        String query = "call set_driver_status(" + driverID + ",'" + status + "')";

        try {
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void update(long driverID, String status) {
        String query = "call update_driver_status(" + driverID + "," + "'" + status + "');";

        try {
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_driver_status(" + id + ")";

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
