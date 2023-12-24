import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.*;

public class ParkingLotTest {

    private static int MAX_CAPACITY = 100;

    private Car car1;
    private Car car2;
    private ParkingLot parkingLot1;
    private ParkingAttendent driver1;
    private Manager manager;

    
    @BeforeEach
    public void setUpBeforeEach() {
        car1 = new Car("UP1234", "Mahindra", "Black", "SUV");
        car2 = new Car("MP1234", "Toyota", "Blue", "Compact");
        parkingLot1 = new ParkingLot(MAX_CAPACITY, "AB");
        driver1 = new ParkingAttendent("Alex");
        manager = new Manager();
        driver1.addParkingLot(parkingLot1);
        manager.addDrivers(driver1);
    }

    @Test
    public void testCarParking() {
        boolean isParked = manager.parkCarByDriver("Alex", car1);
        assertTrue(isParked);
        // Add more assertions or test steps as needed
    }
}
