package Rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vehicle.Car.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Truck track;
    Special special;
    Place place;
    Controller controller;
    @BeforeEach
    public void setUp() throws Exception {
        track = new Truck(1,"Mercedes", "Transit", 2.0F, 130, false,10,2005,2,11000);
        special = new Special(2,"Lingdon", "Asla", 5.0F, 80, false,15,2010,1,"Wóz oborniczy");
        place= new Place();
        controller= new Controller(place);

    }
    @Test
    public void ControllerTest() {
        place.addCar(track);
        place.addCar(special);
        assertEquals(track,controller.findCarAvailble(1));
        assertEquals(2,place.availbleCars.size());
        assertEquals(0,place.rentedCars.size());
        assertEquals(track,controller.handOverCar(1));
        assertEquals(1,place.availbleCars.size());
        assertEquals(1,place.rentedCars.size());
        controller.takeCar(1);
        assertEquals(2,place.availbleCars.size());
        assertEquals(0,place.rentedCars.size());

    }

}