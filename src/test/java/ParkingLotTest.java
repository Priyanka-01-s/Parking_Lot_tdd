import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.ParkingLot;

public class ParkingLotTest {

    private int MAX_CAPACITY =100;
    private int NO_SPACE =0;

    //For parking the car
   @Test
    public void carParkallow(){
        ParkingLot parkingLot = new ParkingLot(MAX_CAPACITY);
        assertTrue(parkingLot.parkCar());
    }

    //for unparking the car
    @Test
    public void carUnpark(){
        ParkingLot parkingLot = new ParkingLot(NO_SPACE);
        assertFalse(parkingLot.parkCar());
    }

    //check if parking is full
    @Test
    public void notifyWhenLotIsFull() {
        ParkingLot parkingLot = new ParkingLot(2);

        assertFalse(parkingLot.isFull());
        parkingLot.parkCar(); 
        assertFalse(parkingLot.isFull()); 
        parkingLot.parkCar(); 
        //after 2 parks
        assertTrue(parkingLot.isFull()); 
    }
}
