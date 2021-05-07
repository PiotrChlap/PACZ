package vehicle.Car;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FamilyPassCar extends PassengerCar{
    private typeFamilyCar type;
    private int seatChild;
    private boolean spareWheel;

    public FamilyPassCar(int id, String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, int numOfDoors, int numOfSeats, typeFamilyCar type, int seatChild, boolean spareWheel) {
        super(id,marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction, numOfDoors, numOfSeats);
        this.type = type;
        this.seatChild = seatChild;
        this.spareWheel = spareWheel;
    }

    public double getPrice() {
        int ratio=1;
        if (isSpareWheel()) {
            ratio*=1.05;
        }
        if (getSeatChild()>=3) {
            ratio*=1.1;
        }
        if(getType().equals(typeFamilyCar.SUV)) {
            ratio*=1.4;
        } else if(getType().equals(typeFamilyCar.Van)) {
            ratio*=1.3;
        } else if(getType().equals(typeFamilyCar.miniVan)) {
            ratio*=1.2;
        } else if(getType().equals(typeFamilyCar.KombiVan)) {
            ratio*=1.15;
        } else {
            ratio*=1.05;
        }
        return ratio*super.getPrice();
    }

    public String getInfoCar() {
        String tmp= super.getInfoCar()+", Typ auta: " + type + ", Foteliki dla dzieci: " + seatChild
                + ", Koło zapasowe: "+ spareWheel;
        return tmp;
    }

    public int getSeatChild() {
        return seatChild;
    }

    public boolean isSpareWheel() {
        return spareWheel;
    }

    public void setType(typeFamilyCar type) {
        this.type = type;
    }

    public typeFamilyCar getType() {
        return type;
    }

    public void setSeatChild(int seatChild) {
        this.seatChild = seatChild;
    }

    public void setSpareWheel(boolean spareWheel) {
        this.spareWheel = spareWheel;
    }

}
