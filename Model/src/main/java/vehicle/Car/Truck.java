package vehicle.Car;

public class Truck extends Car{
    private int maximumLoad;

    public Truck(int id,String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, int numOfDoors, int maximumLoad) {
        super(id,marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction, numOfDoors);
        this.maximumLoad=maximumLoad;
    }


    public void setMaximumLoad(int maximumLoad) {
        this.maximumLoad = maximumLoad;
    }

    @Override
    public int getNumOfDoors() {
        return super.getNumOfDoors();
    }

    @Override
    public double getPrice(){
        int load=getMaximumLoad();
        double ratio = 1;
        if(load>10000) {
            ratio*=1.35111;
        } else if(load > 5000) {
            ratio*=1.3;
        } else {
            ratio*=1.2;
        }

        return super.getPrice()*ratio;
    }

    public String getInfoCar() {
        String tmp= super.getInfoCar()+ ", Maksymalna ładowność: " + maximumLoad;
        return tmp;
    }

    public int getMaximumLoad() {
        return maximumLoad;
    }
}
