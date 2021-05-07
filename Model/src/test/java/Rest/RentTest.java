package Rest;

import Client.Business.BasicBussinessClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vehicle.Car.Truck;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RentTest {
    BasicBussinessClient basicBC;
    Truck track;
    @BeforeEach
    public void setUp() throws Exception {
        //Clienci
        basicBC = new BasicBussinessClient(1000, "Ul.Wiewiórkowa 33 90-525 Łódź","","", "90902255", "Alamandra", "Jarosław Alimun", "905255444");
        track = new Truck(1,"Mercedes", "Transit", 2.0F, 130, false, 10, 2005, 2, 11000);
    }
        @Test
    public void  RentTest(){
        Rent rent = new Rent(1020, LocalDate.of(2020,10,10),LocalDate.of(2020,12,1),track);
        assertTrue(rent.getInfo().equals("Id: 1020 Czas rozpoczęcia: 2020-10-10 Czas zakończenia: 2020-12-01. Wypożyczone auto: Id auta: 1, Marka: Mercedes, Model: Transit, Pojemność: 2.0, Moc: 130, Automatyczna skrzynia biegów: false, Rok produkcji: 2005, Liczba drzwi: 2, Maksymalna ładowność: 11000"));
        assertEquals(294.27,rent.calculateCost(basicBC));
        assertEquals(21, rent.getRentalTime());


    }

}