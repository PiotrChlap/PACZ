package Rest;

import Client.Business.BasicBussinessClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vehicle.Car.Special;
import vehicle.Car.Truck;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    BasicBussinessClient basicBC;
    Truck track;
    Special special;
    Rent r1;
    Rent r2;
    Order order;
    @BeforeEach
    public void setUp() throws Exception {
        basicBC = new BasicBussinessClient(1000, "Ul.Wiewiórkowa 33 90-525 Łódź","","", "90902255", "Alamandra", "Jarosław Alimun", "905255444");
        track = new Truck(1,"Mercedes", "Transit", 2.0F, 130, false, 10, 2005, 2, 11000);
        r1=new Rent(1, LocalDate.of(2020,10,1),LocalDate.of(2020,10,5),track);
        special = new Special(1,"Lingdon", "Asla", 5.0F, 80, false,15,2010,1,"Wóz oborniczy");
        r2 = new Rent(2,LocalDate.of(2020,11,1),LocalDate.of(2020,12,1),special );
        order= new Order(0,LocalDate.of(2020, 9,25),basicBC);
    }

    @Test
    public void orderTest() {
       assertTrue(order.addRent(new Rent(1,LocalDate.of(2020,10,1),LocalDate.of(2020,10,5),track)));

    }

}