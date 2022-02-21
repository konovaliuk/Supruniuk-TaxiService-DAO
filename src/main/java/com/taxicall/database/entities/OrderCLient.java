package com.taxicall.database.entities;

public class OrderCLient {
    private long client_id;
    private String origin_address;
    private String destination_address;
    private int distance;
    private int number_of_people;
    private long car_type_id;

    public OrderCLient(long client_id, String origin_address, String destination_address,
                       int distance, int number_of_people, long car_type_id) {
        this.client_id = client_id;
        this.origin_address = origin_address;
        this.destination_address = destination_address;
        this.distance = distance;
        this.number_of_people = number_of_people;
        this.car_type_id = car_type_id;
    }

    public long getClientID() {
        return client_id;
    }

    public String getOriginAddress() {
        return origin_address;
    }

    public String getDestinationAddress() {
        return destination_address;
    }

    public int getDistance() {
        return distance;
    }

    public int getNumberOfPeople() {
        return number_of_people;
    }

    public long getCarTypeId() {
        return car_type_id;
    }

}
