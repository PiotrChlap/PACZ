package vehicle.Car;

import vehicle.Vehicle;

public abstract class Car extends Vehicle {
    private int numOfDoors;

    public Car(int id,String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, int numOfDoors) {
        super(id, marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction );
        this.numOfDoors=numOfDoors;
    }
    @Override
    public double getPrice() {
        int doors=getNumOfDoors();
        if(doors>6) {
            return super.getPrice()*1.3;
        }else if(doors>=4) {
            return super.getPrice()*1.2;
        } else  {
            return super.getPrice()*1.1;
        }
    }

    public String getInfoCar() {
        String tmp = super.getInfoCar() + ", Liczba drzwi: "+ numOfDoors;
        return tmp;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }
}
