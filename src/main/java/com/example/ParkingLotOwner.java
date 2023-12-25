package com.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotOwner {

    private boolean lotFull;
    private List<SecurityStaff> staffs;

    public ParkingLotOwner() {
        this.lotFull = false;
        this.staffs = new ArrayList<>();
    }

    public boolean setLotFull() {
        if (!lotFull) {
            notifySecurityStaff();
            return true;
        }else{
            notifySpaceAvailable();
            return false;
        }
    }

    public void addStaff(SecurityStaff securityStaff){
        staffs.add(securityStaff);
    }

    public void removeStaff(SecurityStaff securityStaff){
        staffs.remove(securityStaff);
    }

    private void notifySecurityStaff() {
        for (SecurityStaff staff : staffs) {
            staff.notifyLotFull();
        } 
    }

    public void notifySpaceAvailable() {
        System.out.println("Parking Lot has space available. Take in the Full Sign.");
    }
}


