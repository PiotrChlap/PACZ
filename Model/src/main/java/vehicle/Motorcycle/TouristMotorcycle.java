package vehicle.Motorcycle;

public class TouristMotorcycle  extends Motorcycle{
    private int bootCapacity;

    public TouristMotorcycle(int id,String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, driveTypeMotorcycle driveType, int bootCapacity) {
        super(id,marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction, driveType);
        this.bootCapacity = bootCapacity;
    }

    public String getInfoCar() {
        String tmp = super.getInfoCar() + ", Rozmiar dodatkowych bagażników: " + bootCapacity;
        return tmp;

    }

    @Override
    public double getPrice() {
        int capacity = getBootCapacity();
        double ratio=1;
        if (capacity>100) {
            ratio*=1.1;
        } else if (capacity>50){
            ratio*=1.05;
        } else {
            ratio*=1.01;
        }
        return super.getPrice()*ratio;

    }


    public void setBootCapacity(int bootCapacity) {
        this.bootCapacity = bootCapacity;
    }

    public int getBootCapacity() {
        return bootCapacity;
    }
}
