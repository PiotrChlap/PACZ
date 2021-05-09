package Client;

import Client.Business.BasicBussinessClient;
import Client.Business.PremiumBusinessClient;
import Client.Individual.BasicIndClient;
import Client.Individual.DiamondIndClient;
import Client.Individual.SilverIndClient;
import Rest.Controller;
import Rest.Order;
import Rest.Place;
import Rest.Rent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vehicle.Car.*;
import vehicle.Motorcycle.*;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    BasicBussinessClient basicBC;
    PremiumBusinessClient premBC;
    BasicIndClient basicIC;
    DiamondIndClient diamonIC;
    SilverIndClient silverIC;
    Truck track;
    Special special;
    FamilyPassCar Family;
    PremiumPassCar Premium;
    SportPassCar sportcar;
    Chopper chopper;
    Cross cross;
    SportMotorcycle sportMotor;
    TouristMotorcycle touristMotor;
    Controller controller;
    Place place;
    Order order;
    @BeforeEach
    public void setUp() throws Exception {
        //Clienci
        basicBC=new BasicBussinessClient(1000,"Ul.Wiewiórkowa 33 90-525 Łódź","","","90902255","Alamandra","Jarosław Alimun","905255444");
        premBC = new PremiumBusinessClient(2000,"Ul.Sezamowkowa 24 90-233 Warszawa","","","4343345252", "Iladzkowa","Mirosław Zawiało", "544223146");
        basicIC= new BasicIndClient(4232,"Ul.Kwatery 4 Mirosławice 90-222","","", "Andrzej","Bigus","90532355532");
        diamonIC = new DiamondIndClient(345,"Ul.Kalarepy 34 25-453 Kostańczyn","","", "Mirosław","Kaleta","354235235235");
        silverIC = new SilverIndClient(222,"Ul.Asla 22 35-433 Warszawa","","","Alan", "Makłowicz", "06555653434");
        //Pojazdy
        track = new Truck(2,"Mercedes", "Transit", 2.0F, 130, false,10,2005,2,11000);
        special = new Special(3,"Lingdon", "Asla", 5.0F, 80, false,15,2010,1,"Wóz oborniczy");
        Family = new FamilyPassCar(1,"Rogwer", "Grand", 3.2F,120,true,8,2006,5,7,typeFamilyCar.SUV,3,true);
        sportcar = new SportPassCar(1,"Ferrari","Huragan",3.5F,450,false,20,2015,3,2,true,true,320);
        Premium= new PremiumPassCar(1,"Rover", "95", 2.4F,150,false,14,1996,5,5,false,false,true);
        chopper=new Chopper(1,"Crysler","Rose",1.5f,80,false,6,2005, driveTypeMotorcycle.Łańcuch,2.5f);
        cross = new Cross(1,"Ryze","discover",2.5f,200,false,16,2016,driveTypeMotorcycle.Łańcuch,250);
        sportMotor=new SportMotorcycle(1,"Rubin","Rase",2.5f,150,false,20,2018,driveTypeMotorcycle.Wał_kardana,320);
        touristMotor= new TouristMotorcycle(1,"Halsi","Roge",1.5f,80,true,14,2020,driveTypeMotorcycle.Wał_kardana,80);
        place= new Place();
        controller= new Controller(place);



    }
    @Test
    public void ClientTest() {
        assertEquals(0.95,basicBC.getDiscount(special));
        assertEquals(0.855,premBC.getDiscount(track));
        assertEquals(0.99,basicIC.getDiscount(chopper));
        assertEquals(0.96,silverIC.getDiscount(chopper));
        assertEquals(0.95,diamonIC.getDiscount(sportcar));
        assertEquals(0.90,diamonIC.getDiscount(track));
        diamonIC.setLoyaltyPoints(1000005);
        assertEquals(0.8955,diamonIC.getDiscount(track));
        LocalDate l1 = LocalDate.of(2020,12,1);
        LocalDate l2 = LocalDate.of(2020,12,10);
        order = new Order(1,LocalDate.now(),basicBC);
        basicBC.addOrder(order);
        basicBC.setController(controller);
        place.addCar(track);
        place.addCar(special);
        place.addCar(Family);
        basicBC.findOrder(1).addRent(new Rent(1,LocalDate.of(2021,5,1),LocalDate.of(2021,5,29),basicBC.getController().handOverCar(2)));
        basicBC.findOrder(1).addRent(new Rent(2 ,LocalDate.of(2021,6,1),LocalDate.of(2021,7,29),basicBC.getController().handOverCar(3)));
        System.out.println(basicBC.getInfoOrders());
        assertTrue(basicBC.getInfoOrders().equals("1. Zamówienie \n" +
                "Data złożenia: " + LocalDate.now() + "\n"+
                "1. Wypożyczenie: \n" +
                "Id: 1 Czas rozpoczęcia: 2021-05-01 Czas zakończenia: 2021-05-29. Wypożyczone auto: Id auta: 2, Marka: Mercedes, Model: Transit, Pojemność: 2.0, Moc: 130, Automatyczna skrzynia biegów: false, Rok produkcji: 2005, Liczba drzwi: 2, Maksymalna ładowność: 11000\n" +
                "Koszt: \n" +
                "387.9\n" +
                "2. Wypożyczenie: \n" +
                "Id: 2 Czas rozpoczęcia: 2021-06-01 Czas zakończenia: 2021-07-29. Wypożyczone auto: Id auta: 3, Marka: Lingdon, Model: Asla, Pojemność: 5.0, Moc: 80, Automatyczna skrzynia biegów: false, Rok produkcji: 2010, Liczba drzwi: 1, Typ: Wóz oborniczy\n" +
                "Koszt: \n" +
                "681.86\n"));
        assertEquals(2, place.getRentedCars().size());
        basicBC.findOrder(1).closeRent(1);
        assertFalse(basicBC.closeOrder(1));
        basicBC.findOrder(1).closeRent(2);
        assertTrue(basicBC.closeOrder(1));
        assertTrue(basicBC.getInfoOrders().equals("Brak aktualnych zamówień"));
    }

}