package com.taxicall.database.dao;

import com.taxicall.database.Main;
import com.taxicall.database.dao.interfaces.ICarTypeDAO;
import com.taxicall.database.entities.CarType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarTypeDaoImpl implements ICarTypeDAO {
    private final String COLUMN_ID = "id";
    private final String COLUMN_TYPENAME = "typename";
    private final String COLUMN_DESCRIPTION = "description";

    private CarType getCarType(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String type = resultSet.getString(COLUMN_TYPENAME);
        String description = resultSet.getString(COLUMN_DESCRIPTION);

        return new CarType(id, type, description);
    }

    public List<CarType> findAll() {
        String query = "select * from car_types;";
        List<CarType> carTypes = new ArrayList<>();

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            System.out.println("id" + "\t\t" + "typename" +  "\t\t" + "description");

            while (resultSet.next()) {
                CarType carType = getCarType(resultSet);
                carTypes.add(carType);
                System.out.println(carType.getId() + "\t\t" + carType.getTypename()
                        + "\t\t\t\t" + carType.getDescription());
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return carTypes;
    }

    public CarType findByID(long id) {
        String query = "select * from car_types where id=" + id;
        CarType carType = null;

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while(resultSet.next()){
                carType = getCarType(resultSet);

                System.out.println("id" + "\t\t" + "typename" +  "\t\t" + "description");
                System.out.println(carType.getId() + "\t\t" + carType.getTypename()
                        + "\t\t\t\t" + carType.getDescription());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return carType;
    }

    public CarType findByTypename(String typename) {
        String query = "select * from car_types where typename='" + typename+"'";
        CarType carType = null;

        try {
            ResultSet resultSet = Main.statement.executeQuery(query);

            while(resultSet.next()){
                carType = getCarType(resultSet);

                System.out.println("id" + "\t\t" + "typename" +  "\t\t" + "description");
                System.out.println(carType.getId() + "\t\t" + carType.getTypename()
                        + "\t\t\t\t" + carType.getDescription());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return carType;
    }

    public void save(String typename, String description) {
        String query = "call create_type('"+typename+"','"+description+"')";

        try {
            Main.statement.execute(query);
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void update(long id, String typename, String description) {
        String query = "call update_type(" + id + ",'" + typename + "','"+description+"');";

        try {
            Main.statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_type("+id+")";

        try {
            Main.statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
