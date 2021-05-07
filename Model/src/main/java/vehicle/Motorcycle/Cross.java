package vehicle.Motorcycle;

public class Cross extends Motorcycle{
    private int torque;


    public Cross(int id,String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, driveTypeMotorcycle driveType, int torque) {
        super(id,marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction, driveType);
        this.torque = torque;
    }

    public String getInfoCar() {
        String tmp = super.getInfoCar()+ ", Moment obrotny: "+ torque;
        return tmp;
    }

    @Override
    public double getPrice() {
        float torq = getTorque();
        double ratio=1;
        if (torq>450) {
            ratio*=1.3;
        } else if (torq>250){
            ratio*=1.2;
        } else {
            ratio*=1.1;
        }
        return super.getPrice()*ratio;

    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public int getTorque() {
        return torque;
    }
}
