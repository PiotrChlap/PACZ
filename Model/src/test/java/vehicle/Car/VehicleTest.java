package vehicle.Car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vehicle.Motorcycle.*;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    Truck track;
    Special special;
    FamilyPassCar Family;
    PremiumPassCar Premium;
    SportPassCar sportcar;
    Chopper chopper;
    Cross cross;
    SportMotorcycle sportMotor;
    TouristMotorcycle touristMotor;
    @BeforeEach
    public void setUp() throws Exception {
        track = new Truck(1,"Mercedes", "Transit", 2.0F, 130, false,10,2005,2,11000);
        special = new Special(1,"Lingdon", "Asla", 5.0F, 80, false,15,2010,1,"Wóz oborniczy");
        Family = new FamilyPassCar(1,"Rogwer", "Grand", 3.2F,120,true,8,2006,5,7,typeFamilyCar.SUV,3,true);
        sportcar = new SportPassCar(1,"Ferrari","Huragan",3.5F,450,false,20,2015,3,2,true,true,320);
        Premium= new PremiumPassCar(1,"Rover", "95", 2.4F,150,false,14,1996,5,5,false,false,true);
        chopper=new Chopper(1,"Crysler","Rose",1.5f,80,false,6,2005, driveTypeMotorcycle.Łańcuch,2.5f);
        cross = new Cross(1,"Ryze","discover",2.5f,200,false,16,2016,driveTypeMotorcycle.Łańcuch,250);
        sportMotor=new SportMotorcycle(1,"Rubin","Rase",2.5f,150,false,20,2018,driveTypeMotorcycle.Wał_kardana,320);
        touristMotor= new TouristMotorcycle(1,"Halsi","Roge",1.5f,80,true,14,2020,driveTypeMotorcycle.Wał_kardana,80);

    }
    @Test
    public void constructorCarsTest() {
        assertTrue(track.getMarka().equals("Mercedes"));
        assertTrue(track.getModel().equals("Transit"));
        assertEquals(2.0f,track.getCapacity());
        assertEquals(130,track.getPower());
        assertFalse(track.isAutomaticGearbox());
        assertEquals(2005,track.getYearOfProduction());
        assertEquals(2,track.getNumOfDoors());
        assertEquals(11000,track.getMaximumLoad());
        assertTrue("Wóz oborniczy".equals(special.getType()));
        assertTrue(typeFamilyCar.SUV.equals(Family.getType()));
        assertEquals(3,Family.getSeatChild());
        assertTrue(Family.isSpareWheel());
        assertTrue(sportcar.isChipTuning());
        assertTrue(sportcar.isSpoiler());
        assertEquals(320,sportcar.getMaxSpeed());
        assertFalse(Premium.isAutopilot());
        assertFalse(Premium.isGlassRoof());
        assertTrue(Premium.isBar());
        assertTrue(driveTypeMotorcycle.Łańcuch.equals(chopper.getDriveType()));
        assertEquals(2.5f,chopper.getLength());
        assertTrue(driveTypeMotorcycle.Łańcuch.equals(cross.getDriveType()));
        assertEquals(250,cross.getTorque());
        assertEquals(320,sportMotor.getMaxSpeed());
        assertEquals(80,touristMotor.getBootCapacity());

        System.out.println(track.getClass().getSimpleName());
    }


}