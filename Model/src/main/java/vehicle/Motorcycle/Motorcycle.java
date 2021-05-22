package vehicle.Motorcycle;

import vehicle.Vehicle;

public abstract class Motorcycle extends Vehicle {
    protected driveTypeMotorcycle driveType;

    public Motorcycle(int id,String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, driveTypeMotorcycle driveType) {
        super(id,marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction );
        this.driveType = driveType;
    }

    public String getInfoCar() {
        String tmp= super.getInfoCar()+ ", Typ napędu: " + driveType;
        return tmp;
    }

    @Override
    public double getPrice() {
        driveTypeMotorcycle type = getDriveType();
        if (type==driveTypeMotorcycle.Łańcuch) {
            return super.getPrice()*1.3;
        } else if(type==driveTypeMotorcycle.Wał_kardana) {
            return super.getPrice()*1.10;
        } else {
            return super.getPrice();
        }

    }

    public void setDriveType(driveTypeMotorcycle driveType) {
        this.driveType = driveType;
    }

    public driveTypeMotorcycle getDriveType() {
        return driveType;
    }
}
