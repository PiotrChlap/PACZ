package vehicle.Motorcycle;

public class Chopper extends Motorcycle{
    private float length;

    public Chopper(int id,String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, driveTypeMotorcycle driveType, float length) {
        super(id,marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction, driveType);
        this.length = length;
    }

    @Override
    public String getInfoCar() {
        String tmp = super.getInfoCar()+ ", Długość: "+ length;
        return tmp;
    }

    @Override
    public double getPrice() {
        float type = getLength();
        double ratio = 1;
        if (type>3) {
            ratio*=1.2;
        } else {
            ratio*=1.1;
        }
        return super.getPrice()*ratio;

    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getLength() {
        return length;
    }
}
