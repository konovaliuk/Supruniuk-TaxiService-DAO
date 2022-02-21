package com.taxicall.database.entities;

public class OrderDriver {
    private long id;
    private long driver_id;
    private String waiting_time;
    private String order_status;
    private long car_id;

    public OrderDriver(long id, long driver_id, String waiting_time,
                       String order_status, long car_id) {
        this.id = id;
        this.driver_id = driver_id;
        this.waiting_time = waiting_time;
        this.order_status = order_status;
        this.car_id = car_id;
    }

    public long getID() {
        return id;
    }

    public long getDriverID() {
        return driver_id;
    }

    public String getOrderStatus() {
        return order_status;
    }

    public String getWaitingTime() {
        return waiting_time;
    }

    public long getCarId() {
        return car_id;
    }

}
