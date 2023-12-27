import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.*;

public class ParkingLotTest {

    private static int MAX_CAPACITY = 100;
    private Car car1;
    private Car car2;
    private Car car3;
    private Car car4;
    private Car car5;
    private Car car6;
    private Car handicapDriverCar;
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private ParkingLot parkingLot3;
    private ParkingAttendent driver1;
    private ParkingAttendent driver2;
    private Manager manager1;
    private Manager manager2;
    private ParkingLotOwner owner;
    private SecurityStaff staff;

    @BeforeEach
    public void setUpBeforeEach() {
        car1 = new Car("UP1234", "Mahindra", "Black", "SUV", false);
        car2 = new Car("MP1234", "Toyota", "Blue", "Compact",false);
        car3 = new Car("MH0102", "BWM", "White", "Sedan",false);
        car4 = new Car("KA5678", "Ford", "Red", "Sedan",false);
        car5 = new Car("TN9876", "BMW", "Green", "Compact",false);
        car6 = new Car("DL5432", "Toyota", "Blue", "SUV",false);
        handicapDriverCar = new Car("KA5678", "Ford", "Red", "Sedan", true);
        parkingLot1 = new ParkingLot(MAX_CAPACITY, "AB");
        parkingLot2 = new ParkingLot(1, "XY");
        parkingLot3 = new ParkingLot(3, "CD");
        driver1 = new ParkingAttendent("Alex");
        driver2 = new ParkingAttendent("John");
        manager1 = new Manager();
        manager2 = new Manager();
        driver1.addParkingLot(parkingLot1);
        driver2.addParkingLot(parkingLot2);
        manager1.addDrivers(driver1);
        manager2.addDrivers(driver2);
        owner = new ParkingLotOwner();
        staff = new SecurityStaff("Max");

    }

    // test for car parking
    @Test
    public void testCarParking() {
        // test parking car1
        boolean isParkedCar1 = manager1.parkCarByDriver("Alex", car1);
        assertTrue(isParkedCar1);
        assertEquals(99, parkingLot1.getAvailableSpaces());
        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertEquals("AB", car1.getLocation());

        // test parking car2
        boolean isParkedCar2 = manager2.parkCarByDriver("John", car2);
        assertTrue(isParkedCar2);
        assertEquals(0, parkingLot2.getAvailableSpaces());
        assertTrue(parkingLot2.getParkedCars().contains(car2));
        assertEquals("XY", car2.getLocation());
    }

    // Test for car unparking
    @Test
    public void testCarUnparking() {
        manager2.parkCarByDriver("John", car2);
        assertTrue(parkingLot2.getParkedCars().contains(car2));// yes

        boolean isUnparkedCar2 = manager2.unparkCarByDriver("John", car2);
        assertTrue(isUnparkedCar2);
        assertEquals(1, parkingLot2.getAvailableSpaces()); // parking space increased
        assertFalse(parkingLot2.getParkedCars().contains(car2));
    }

    // notify when parking lot is full to the owner
    @Test
    public void testNotifyWhenFull() {
        parkingLot2.setOwner(owner);

        assertTrue(parkingLot2.parkCar(car2));
        assertTrue(owner.setLotFull());
    }

    // test to redirect security staff when lot is full
    @Test
    public void testSecurityWhenFull() {
        owner.addStaff(staff);
        parkingLot2.setOwner(owner);

        assertTrue(parkingLot2.parkCar(car2));
        assertTrue(owner.setLotFull());
        // notify the airport staff
        staff.notifyLotFull();

    }

    // test for notifying when the lot has space again
    @Test
    public void testNotifyWhenSpaceAvailable() {
        assertTrue(parkingLot3.parkCar(car1));
        assertTrue(parkingLot3.parkCar(car2));
        assertTrue(parkingLot3.parkCar(car3));

        assertEquals(0, parkingLot3.getAvailableSpaces());

        assertTrue(parkingLot3.unparkCar(car2));
        assertTrue(parkingLot3.unparkCar(car3));

        assertFalse(parkingLot3.checkLotFull());
        staff.notifyLotNotFull();
    }

    // Test where to park the car by a attendant
    @Test
    public void testOwnerDecidesParking() {
        parkingLot1.setOwner(owner);
        driver1.addParkingLot(parkingLot1);
        boolean isParkedCar1 = manager1.parkCarByDriver("Alex", car1);

        assertTrue(isParkedCar1);
        assertEquals(99, parkingLot1.getAvailableSpaces());
        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertEquals("AB", car1.getLocation());
        assertTrue(owner.setLotFull());
    }

    // test driver to find the car in parking lot
    @Test
    public void testDriverFindsCar() {
        driver1.addParkingLot(parkingLot1);
        boolean isParkedCar1 = manager1.parkCarByDriver("Alex", car1);
        assertTrue(isParkedCar1);

        String foundLocation = driver1.findCar("UP1234");
        assertEquals("AB", foundLocation);
    }

    @Test
    public void testOwnerGetsNotificationWhenCarParked() {
        parkingLot1.setOwner(owner);
        driver1.addParkingLot(parkingLot1);
        boolean isParkedCar1 = manager1.parkCarByDriver("Alex", car1);

        assertTrue(isParkedCar1);
        assertEquals(99, parkingLot1.getAvailableSpaces());
        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertEquals("AB", car1.getLocation());
        // owner can retrieve the timestamp for the parked car
        assertNotNull(parkingLot1.getParkingTimestamp(car1));
    }

    //test cars parked in parking lot are even 
    @Test
    public void testEvenDistributionOfCars() {
        parkingLot1.setOwner(owner);
        parkingLot2.setOwner(owner);

        driver1.addParkingLot(parkingLot1);
        driver1.addParkingLot(parkingLot2);

        manager1.parkCarByDriver("Alex", car1);
        manager1.parkCarByDriver("Alex", car2);
        manager1.parkCarByDriver("Alex", car3);
        manager2.parkCarByDriver("John", car4);
        manager2.parkCarByDriver("John", car5);
        manager2.parkCarByDriver("John", car6);

        int expectedAverageCarsPerLot = 3;
        int actualCarsInLot1 = parkingLot1.getParkedCars().size();
        int actualCarsInLot2 = parkingLot2.getParkedCars().size();

        System.out.println("Actual Cars in Lot 1: " + actualCarsInLot1);
        System.out.println("Actual Cars in Lot 2: " + actualCarsInLot2);
        assertEquals(expectedAverageCarsPerLot, actualCarsInLot1);
        assertEquals(expectedAverageCarsPerLot, actualCarsInLot2);
    }

    @Test
    public void testParkNearestAvailableSpaceForHandicapDriver() {
        // Set the owner for all parking lots
        parkingLot1.setOwner(owner);
        manager1.parkCarByDriver("Alex", car1);
        manager1.parkCarByDriver("Alex", car2);
        manager1.parkCarByDriver("Alex", car3);
        manager1.parkCarByDriver("Alex", handicapDriverCar);
        
        String expectedLocation = parkingLot1.getLocation(); 
        String actualLocation = handicapDriverCar.getLocation();

        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void testPoliceDepartmentInvestigation() {
        parkingLot1.setOwner(owner);
        manager1.parkCarByDriver("Alex", car3);

        List<String> locationsOfWhiteCars = parkingLot1.getLocationsOfAllParkedWhiteCars();
        assertTrue(locationsOfWhiteCars.contains("AB"));
    }

    @Test
    public void testGetDetailsOfParkedBlueToyotaCars() {
        manager1.parkCarByDriver("Alex", car6);
        parkingLot1.addParkingAttendant(driver1);

        List<String> details = parkingLot1.getDetailsOfParkedBlueToyotaCars();
        assertEquals(1, details.size());
        assertEquals("Plate Number: DL5432, Location: AB, Parking Attendant: Alex", details.get(0));
    }

    @Test
    public void testGetLocationsOfParkedBMW() {
        manager1.parkCarByDriver("Alex", car3);
        manager1.parkCarByDriver("Alex",car5);

        assertEquals(List.of("AB"), parkingLot1.getLocationsOfParkedBMW());
    }

}
