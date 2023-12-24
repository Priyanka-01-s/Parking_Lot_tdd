package com.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingAttendent {
    
    private String name;
    private List<ParkingLot> parkingLotList;

    public ParkingAttendent(String name){
        this.name = name;
        this.parkingLotList = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void addParkingLot(ParkingLot parkingLot){
        parkingLotList.add(parkingLot);
    }

    public boolean FindSpaceAndPark(Car car){
        String location = "";
        for (int i=0;i < parkingLotList.size();i++){
            if (parkingLotList.get(i).parkCar(car)){
                location = this.parkingLotList.get(i).getLocation();
                return true;
            }
            System.out.println("Car is parked at "+location+" spot");
        }
        return false;
    }

    public boolean unParkCar(Car car){
        for(ParkingLot lot:parkingLotList){
            if(lot.getLocation().equals(car.location)){
                return lot.unparkCar(car);
            }
        }

        return false;
    }
}
