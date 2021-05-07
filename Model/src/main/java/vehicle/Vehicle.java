package vehicle;

public abstract class Vehicle {
    protected int carId;
    protected String marka;
    protected String model;
    protected float capacity;
    protected int power;
    protected boolean automaticGearbox;
    protected int basicPrice;
    protected int yearOfProduction;

    public Vehicle(int id,String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction) {
        this.marka = marka;
        this.model = model;
        this.capacity = capacity;
        this.power = power;
        this.automaticGearbox = automaticGearbox;
        this.basicPrice = basicPrice;
        this.yearOfProduction = yearOfProduction;
        this.carId=id;
    }
    public double getPrice(){
        return basicPrice;
    }

    public String getInfoCar() {
        String tmp = "Id auta: "+ carId + ", Marka: " + marka+ ", Model: " + model+
                ", Pojemność: " + capacity + ", Moc: " + power+ ", Automatyczna skrzynia biegów: " + automaticGearbox
                + ", Rok produkcji: " + yearOfProduction;
        return tmp;
    }

    public int getCarId() {
        return carId;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public float getCapacity() {
        return capacity;
    }

    public int getPower() {
        return power;
    }

    public boolean isAutomaticGearbox() {
        return automaticGearbox;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setAutomaticGearbox(boolean automaticGearbox) {
        this.automaticGearbox = automaticGearbox;
    }

    public void setBasicPrice(int basicPrice) {
        this.basicPrice = basicPrice;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

}
