package com.example;

public class SecurityStaff {
    private String name;

    public SecurityStaff(String name) {
        this.name = name;
    }

    public void notifyLotFull() {
        System.out.println("Security personnel " + name + " notified: Parking Lot is Full.\nRedirecting staff.");
    }

    public void notifyLotNotFull() {
        System.out.println("Parking Lot is not Full. You can remove the Full Sign.");
    }
    
}
