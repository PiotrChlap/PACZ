package vehicle.Car;

public class Special extends Car{
    private String type;

    public Special(int id, String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, int numOfDoors,String type) {
        super(id ,marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction, numOfDoors);
        this.type=type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice(){
        return super.getPrice()*1.5;
    }

    public String getInfoCar() {
        String tmp= super.getInfoCar()+", Typ: " + type;
        return tmp;
    }

    public String getType() {
        return type;
    }
}
