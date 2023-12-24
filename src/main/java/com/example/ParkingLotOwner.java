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

    public boolean isLotFull() {
        System.out.println("Parking Lot is Full! Put out the Full Sign.");
        lotFull = true;
        notifySecurityStaff();
        return lotFull;
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
}
