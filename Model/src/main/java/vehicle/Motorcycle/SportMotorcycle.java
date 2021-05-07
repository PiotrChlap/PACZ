package vehicle.Motorcycle;

public class SportMotorcycle extends Motorcycle{
    private int maxSpeed;

    public SportMotorcycle(int id,String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, driveTypeMotorcycle driveType, int maxSpeed) {
        super(id, marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction, driveType);
        this.maxSpeed = maxSpeed;
    }
    public String getInfoCar() {
        String tmp = super.getInfoCar() + ", Maksymalna prędkość: " + maxSpeed;
        return tmp;
    }

    @Override
    public double getPrice() {
        float speed = getMaxSpeed();
        double ratio=1;
        if (speed>350) {
            ratio*=1.3;
        } else if (speed>200){
            ratio*=1.2;
        } else {
            ratio*=1.1;
        }
        return super.getPrice()*ratio;

    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
