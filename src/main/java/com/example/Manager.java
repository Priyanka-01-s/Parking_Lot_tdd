package com.example;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private List<ParkingAttendent> carDrivers;

    public Manager(){
        this.carDrivers = new ArrayList<>();
    }

    public void addDrivers(ParkingAttendent drivers){
        carDrivers.add(drivers);
    }

    public boolean parkCarByDriver(String name, Car car) {
        if (car == null) {
            return false;
        }
        for (ParkingAttendent parkingAttendant : carDrivers) {
            if (parkingAttendant.getName().equals(name)) {
                return parkingAttendant.FindSpaceAndPark(car);
            }
        }
        return true;
    }

    public boolean unparkCarByDriver(String name, Car car) {
        if (car == null) {
            return false;
        }
        for (ParkingAttendent parkingAttendant : carDrivers) {
            if (parkingAttendant.getName().equals(name)) {
                return parkingAttendant.unParkCar(car);
            }
        }
        return true;
    }    
}
