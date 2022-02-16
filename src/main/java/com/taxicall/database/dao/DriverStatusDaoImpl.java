package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.dao.interfaces.IDriverStatusDAO;
import com.taxicall.database.entities.DriverStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverStatusDaoImpl implements IDriverStatusDAO {
    private final String COLUMN_DRIVER_ID = "driver_id";
    private final String COLUMN_STATUS = "status";

    private DriverStatus getDriverStatus(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_DRIVER_ID);
        String status = resultSet.getString(COLUMN_STATUS);

        return new DriverStatus(id, status);
    }

    public List<DriverStatus> findAll() {
        String query = "select * from driver_status;";
        List<DriverStatus> statuses = new ArrayList<>();

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);
            System.out.println("driver id" + "\t\t" + "status");
            while (resultSet.next()) {
                DriverStatus driverStatus = getDriverStatus(resultSet);
                statuses.add(driverStatus);
                System.out.println(driverStatus.getId() + "\t\t\t\t" + driverStatus.getDriverStatus());
            }

            resultSet.close();
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return statuses;
    }

    public DriverStatus findByDriverID(long driverID) {
        String query = "select * from driver_status where driver_id=" + driverID;
        DriverStatus driverStatus = null;

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while(resultSet.next()){
                driverStatus = getDriverStatus(resultSet);
                System.out.println("driver id" + "\t\t" + "status");
                System.out.println(driverStatus.getId() + "\t\t\t\t" + driverStatus.getDriverStatus());
            }

            resultSet.close();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return driverStatus;
    }

    public void save(long driverID, String status) {
        String query = "call set_driver_status("+driverID+",'"+status+"')";

        try {
            Main.statement.execute(query);
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void update(long driverID, String status) {
        String query = "call update_driver_status(" + driverID + "," + "'" + status + "');";

        try {
            Main.statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_driver_status("+id+")";

        try {
            Main.statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
