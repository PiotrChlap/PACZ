package vehicle.Car;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class PassengerCar extends Car{
    protected int numOfSeats;

    public PassengerCar(int id,String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, int numOfDoors, int numOfSeats) {
        super(id,marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction, numOfDoors );
        this.numOfSeats = numOfSeats;
    }

    public double getPrice() {
        int seats=getNumOfSeats();
        if(seats>6) {
            return super.getPrice()*1.2;
        }else if(seats>=4) {
            return super.getPrice()*1.05;
        } else  {
            return super.getPrice();
        }
    }

    public String getInfoCar() {
        String tmp = super.getInfoCar()+ ", Ilość siedzeń: " + numOfSeats;
        return tmp;
    }


    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }
}
