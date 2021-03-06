package Rest;

import Client.Client;
import vehicle.Vehicle;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rent {
    private final int rentId;
    private final LocalDate startDate;
    private LocalDate endDate;
    private final Vehicle rentedVehicle;
    private boolean closed=false;

    public Rent(int rentId, LocalDate startDate, LocalDate endDate, Vehicle rentedVehicle) {
        this.rentId = rentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentedVehicle = rentedVehicle;
    }

    public int getRentId() {
        return rentId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed() {
        this.closed = true;
    }

    public String getInfo() {
        String tmp="Id: "+rentId+" Czas rozpoczęcia: " +startDate+ " Czas zakończenia: " +endDate+".\n\t Wypożyczone auto: " + rentedVehicle.getInfoCar();
        return tmp;
    }



    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double calculateCost(Client client) {
        double cost= (getRentalTime()+1)*rentedVehicle.getPrice()*client.getDiscount(rentedVehicle);
        return (Math.round(cost*100.0)/100.0);
    }

    public long getRentalTime() {

        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
