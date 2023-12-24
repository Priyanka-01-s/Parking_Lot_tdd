package com.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;//100
    private List<Car> parkedCars;
    private String location;

    public ParkingLot(int capacity,String location) {
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
        this.location = location;
    }

    public boolean isFull() {
        return parkedCars.size() == capacity;
    }

    public boolean parkCar(Car car) {
        if (!isFull()) {
            parkedCars.add(car);
            car.location = this.location;
            capacity--;
            return true;
        }
        return false;
    }

    public boolean unparkCar(Car car) {
        parkedCars.remove(car);
        capacity++;
        return true;
    }

    public int getAvailableSpaces() {
        return capacity - parkedCars.size();
    }

    public int getCapacity(){
        return capacity;
    }

    public List<Car> getParkedCar(){
        return parkedCars;
    }

    public String getLocation(){
        return location;
    }
    
}
