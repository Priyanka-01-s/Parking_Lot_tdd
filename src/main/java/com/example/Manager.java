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

    public boolean parkCarByDriver(String name, Car car){
        for(ParkingAttendent parkingAttendent : carDrivers){
            if (parkingAttendent.getName().equals(name)){
                return parkingAttendent.FindSpaceAndPark(car);
            }
        }
        return false;
    }
    
}
