import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.*;

public class ParkingLotTest {

    private static int MAX_CAPACITY = 100;

    private Car car1;
    private Car car2;
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private ParkingAttendent driver1;
    private ParkingAttendent driver2;
    private Manager manager1;
    private Manager manager2;
    private ParkingLotOwner owner;
    private SecurityStaff staff;

    @BeforeEach
    public void setUpBeforeEach() {
        car1 = new Car("UP1234", "Mahindra", "Black", "SUV");
        car2 = new Car("MP1234", "Toyota", "Blue", "Compact");
        parkingLot1 = new ParkingLot(MAX_CAPACITY, "AB");
        parkingLot2 = new ParkingLot(1, "XY");
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

    //test for car parking
    @Test
    public void testCarParking() {
        //test parking car1
        boolean isParkedCar1 = manager1.parkCarByDriver("Alex", car1);
        assertTrue(isParkedCar1);
        assertEquals(99, parkingLot1.getAvailableSpaces());
        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertEquals("AB", car1.getLocation());

        //test parking car2
        boolean isParkedCar2 = manager2.parkCarByDriver("John", car2);
        assertTrue(isParkedCar2);
        assertEquals(0, parkingLot2.getAvailableSpaces());
        assertTrue(parkingLot2.getParkedCars().contains(car2));
        assertEquals("XY", car2.getLocation()); 
    }

    //Test for car unparking
    @Test
    public void testCarUnparking(){
        manager2.parkCarByDriver("John", car2);
        assertTrue(parkingLot2.getParkedCars().contains(car2));//yes

        boolean isUnparkedCar2 = manager2.unparkCarByDriver("John", car2);
        assertTrue(isUnparkedCar2);
        assertEquals(1, parkingLot2.getAvailableSpaces()); //parking space increased
        assertFalse(parkingLot2.getParkedCars().contains(car2));
    }

    //notify when parking lot is full to the owner
    @Test
    public void testNotifyWhenFull(){
        parkingLot2.setOwner(owner); 

        assertTrue(parkingLot2.parkCar(car2));
        assertTrue(owner.isLotFull());
    }

    //test to redirect security staff when lot is full
    @Test
    public void testSecurityWhenFull(){
        owner.addStaff(staff);
        parkingLot2.setOwner(owner);

        assertTrue(parkingLot2.parkCar(car2));
        assertTrue(owner.isLotFull());
        
        //notify the airport staff
        staff.notifyLotFull();

    }
 

}
