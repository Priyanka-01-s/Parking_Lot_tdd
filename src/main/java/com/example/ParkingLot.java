package com.example;

public class ParkingLot {

    private int MAX_CAPACITY =100;
    private int spaceOccupied; 

    public ParkingLot(int MAX_CAPACITY){
        this.MAX_CAPACITY=MAX_CAPACITY;
        this.spaceOccupied = 0;
    }

    public boolean parkCar(){
        if(spaceOccupied < MAX_CAPACITY){
            spaceOccupied++;
            return true;
        }else{
            return false;
        }
    }

    public boolean isFull() {
        return spaceOccupied >= MAX_CAPACITY;
    }
   
}
