package com.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private List<Car> parkedCars;
    private String location;
    private ParkingLotOwner owner;
    private List<SecurityStaff> securityStaffList;

    public ParkingLot(int capacity, String location) {
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
        this.location = location;
        this.owner = null;
        this.securityStaffList = new ArrayList<>();
    }

    public void setOwner(ParkingLotOwner owner) {
        this.owner = owner;
    }

    public void addSecurityStaff(SecurityStaff securityStaff) {
        this.securityStaffList.add(securityStaff);
    }

    public boolean isFull() {
        return parkedCars.size() == capacity;
    }

    public boolean parkCar(Car car) {
        if (!isFull()) {
            parkedCars.add(car);
            car.location = this.location;
            capacity--;
            checkLotFull();
            return true;
        }
        return false;
    }

    public boolean unparkCar(Car car) {
        boolean success = parkedCars.remove(car);
        if (success) {
            car.location = null;
            capacity++;
            checkLotFull();
        }
        return success;
    }

    public int getAvailableSpaces() {
        return capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Car> getParkedCars() {
        return new ArrayList<>(parkedCars);
    }

    public String getLocation() {
        return location;
    }

    public boolean checkLotFull() {
        if (isFull() && owner != null) {
            owner.setLotFull();
            return true;
        }
        return false;
    }
    
    // public void checkLotNotFull() {
    //     if (!isFull() && owner != null) {
    //         owner.notifySpaceAvailable();
    //         notifySecurityStaffLotNotFull();
    //     }
    // }

    public void notifySecurityStaffLotNotFull() {
        for (SecurityStaff staff : securityStaffList) {
            staff.notifyLotNotFull();
        }
    }
}
