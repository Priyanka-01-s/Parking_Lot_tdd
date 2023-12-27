package com.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private int capacity;
    private Map<Car, LocalDateTime> parkedCars;;
    private String location;
    private ParkingLotOwner owner;
    private List<ParkingAttendent> parkingAttendants;
    private List<SecurityStaff> securityStaffList;

    public ParkingLot(int capacity, String location) {
        this.capacity = capacity;
        this.parkedCars = new HashMap<>();
        this.location = location;
        this.owner = null;
        this.securityStaffList = new ArrayList<>();
        this.parkingAttendants = new ArrayList<>();
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
            parkedCars.put(car, LocalDateTime.now()); 
            car.location = this.location;
            capacity--;
            checkLotFull();
            return true;
        }
        return false;
    }

    public boolean unparkCar(Car car) {
        LocalDateTime timestamp = parkedCars.remove(car);
        if (timestamp != null) {
            car.location = null;
            capacity++;
            checkLotFull();
        }
        return timestamp != null;
    }

    public int getAvailableSpaces() {
        return capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Car> getParkedCars() {
        return new ArrayList<>(parkedCars.keySet());
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

    public void notifySecurityStaffLotNotFull() {
        for (SecurityStaff staff : securityStaffList) {
            staff.notifyLotNotFull();
        }
    }

    public LocalDateTime getParkingTimestamp(Car car) {
        return parkedCars.get(car);
    }

    public void addParkingAttendant(ParkingAttendent attendant) {
        this.parkingAttendants.add(attendant);
    }


    public List<String> getLocationsOfAllParkedWhiteCars() {
        return parkedCars.entrySet().stream()
                .filter(entry -> "White".equalsIgnoreCase(entry.getKey().getColor()))
                .map(entry -> entry.getKey().getLocation())
                .collect(Collectors.toList());
    }

    public List<String> getDetailsOfParkedBlueToyotaCars() {
        List<String> detailsList = new ArrayList<>();
        for (Car car : parkedCars.keySet()) {
            if (car.getColor().equalsIgnoreCase("Blue") && car.getModel().equalsIgnoreCase("Toyota")) {
                String attendantName = findAttendantNameForCar(car);
                String details = "Plate Number: " + car.getLisenseNum() +
                                 ", Location: " + location +
                                 ", Parking Attendant: " + attendantName;
                detailsList.add(details);
            }
        }
        return detailsList;
    }
    
    private String findAttendantNameForCar(Car car) {
        for (ParkingAttendent attendant : parkingAttendants) {
            List<ParkingLot> attendantLots = attendant.getParkingLotList();
            for (ParkingLot lot : attendantLots) {
                if (lot.getLocation().equals(car.getLocation())) {
                    return attendant.getName();
                }
            }
        }
        return null;
    }

    public List<String> getLocationsOfParkedBMW() {
        List<String> locations = new ArrayList<>();
        for (Car car : parkedCars.keySet()) {
            if (car.getModel().equalsIgnoreCase("BMW")) {
                locations.add(location);
            }
        }
        return locations;
    }
    
}
