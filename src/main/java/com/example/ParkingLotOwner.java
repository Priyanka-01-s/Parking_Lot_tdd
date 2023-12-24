package com.example;

public class ParkingLotOwner {

    private boolean lotFull;

    public ParkingLotOwner() {
        this.lotFull = false;
    }

    public boolean isLotFull() {
        System.out.println("Parking Lot is Full! Put out the Full Sign.");
        return lotFull;
    }
}
