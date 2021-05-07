import Client.Business.BasicBussinessClient;
import Rest.Invoice;
import Rest.Order;
import Rest.Rent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vehicle.Car.Special;
import vehicle.Car.Truck;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {
    Invoice invoice;
    Invoice invoice2;
    Order order;
    BasicBussinessClient basicBC;
    Truck track;
    Special special;
    Rent r1;
    Rent r2;
    @BeforeEach
    public void setUp() throws Exception {
        basicBC = new BasicBussinessClient(1000, "Ul.Wiewiórkowa 33 90-525 Łódź","","", "90902255", "Alamandra", "Jarosław Alimun", "905255444");
        track = new Truck(1,"Mercedes", "Transit", 2.0F, 130, false, 10, 2005, 2, 11000);
        r1=new Rent(1, LocalDate.of(2020,10,1),LocalDate.of(2020,10,5),track);
        special = new Special(1,"Lingdon", "Asla", 5.0F, 80, false,15,2010,1,"Wóz oborniczy");
        r2 = new Rent(2,LocalDate.of(2020,11,1),LocalDate.of(2020,12,1),special );
        order= new Order(0,LocalDate.of(2020, 9,25),basicBC);
        order.addRent(track,LocalDate.of(2020,10,1),LocalDate.of(2020,10,5));
        order.addRent(special,LocalDate.of(2020,2,1),LocalDate.of(2020,10,5));
        invoice = new Invoice(20212021, LocalDate.of(2002,10,10),LocalDate.of(2002,10,11),true,order);
        invoice2 = new Invoice(20212021, LocalDate.of(2002,10,10),null,false,null);
    }

    @Test
    public void constructorTest() {
        assertEquals(20212021,invoice.getInvoiceId());
        assertTrue(LocalDate.of(2002,10,10).equals(invoice.getDateOfPayment()));
        assertTrue(LocalDate.of(2002,10,11).equals(invoice.getDateOfissue()));
        assertTrue(invoice.isPaid());
    }

    @Test
    public void setterTest() {
        invoice2.setDateOfPayment(LocalDate.of(2002,11,1));
        assertTrue(invoice2.getDateOfPayment().equals(LocalDate.of(2002,11,1)));
        assertTrue(invoice2.isPaid());
        assertTrue(invoice.createInvoice().equals("Klient: Alamandra\n" +
                "90902255\n" +
                "Id: 1000\n" +
                "\n" +
                "Suma:\n" +
                "184.44\n" +
                "Data złożenia: 2020-09-25\n" +
                "1. Wypożyczenie: \n" +
                "Id: 1 Czas rozpoczęcia: 2020-10-01 Czas zakończenia: 2020-10-05. Wypożyczone auto: Id auta: 1, Marka: Mercedes, Model: Transit, Pojemność: 2.0, Moc: 130, Automatyczna skrzynia biegów: false, Rok produkcji: 2005, Liczba drzwi: 2, Maksymalna ładowność: 11000\n" +
                "Koszt: \n" +
                "66.88\n" +
                "2. Wypożyczenie: \n" +
                "Id: 2 Czas rozpoczęcia: 2020-02-01 Czas zakończenia: 2020-10-05. Wypożyczone auto: Id auta: 1, Marka: Lingdon, Model: Asla, Pojemność: 5.0, Moc: 80, Automatyczna skrzynia biegów: false, Rok produkcji: 2010, Liczba drzwi: 1, Typ: Wóz oborniczy\n" +
                "Koszt: \n" +
                "117.56\n"));

    }
}