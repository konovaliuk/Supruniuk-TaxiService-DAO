package com.taxicall.database.entities;

public class OrderDispatcher {
    private long id;
    private long dispatcher_id;
    private boolean approved;
    private String order_status;
    private double total_payment;

    public OrderDispatcher(long id, long dispatcher_id, boolean approved,
                           String order_status, double total_payment) {
        this.id = id;
        this.dispatcher_id = dispatcher_id;
        this.approved = approved;
        this.order_status = order_status;
        this.total_payment = total_payment;
    }

    public long getID() {
        return id;
    }

    public long getDispatcherID() {
        return dispatcher_id;
    }

    public boolean isApproved() {
        return approved;
    }

    public String orderStatus() {
        return order_status;
    }

    public double getTotalPayment() {
        return total_payment;
    }
}
