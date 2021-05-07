package vehicle.Car;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PremiumPassCar extends PassengerCar{
    private boolean autopilot;
    private boolean glassRoof;
    private boolean bar;

    public PremiumPassCar(int id, String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, int numOfDoors, int numOfSeats, boolean autopilot, boolean glassRoof, boolean bar) {
        super(id,marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction, numOfDoors, numOfSeats);
        this.autopilot = autopilot;
        this.glassRoof = glassRoof;
        this.bar = bar;
    }

    public double getPrice() {
        int ratio=1;
        if (isAutopilot()) {
            ratio*=1.5;
        }
        if (isGlassRoof()) {
            ratio*=1.1;
        }
        if (isBar()) {
            ratio*=1.15;
        }
        return ratio*super.getPrice();
    }

    public String getInfoCar() {
        String tmp= super.getInfoCar()+", Auto pilot: " + autopilot + ", Szklany dach: " + glassRoof +
                ", Bar: " + bar;
        return tmp;
    }

    public boolean isAutopilot() {
        return autopilot;
    }

    public boolean isGlassRoof() {
        return glassRoof;
    }

    public boolean isBar() {
        return bar;
    }

    public void setAutopilot(boolean autopilot) {
        this.autopilot = autopilot;
    }

    public void setGlassRoof(boolean glassRoof) {
        this.glassRoof = glassRoof;
    }

    public void setBar(boolean bar) {
        this.bar = bar;
    }
}
