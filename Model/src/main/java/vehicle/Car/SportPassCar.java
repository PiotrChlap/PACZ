package vehicle.Car;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SportPassCar extends PassengerCar{
    private boolean chipTuning;
    private boolean spoiler;
    private int maxSpeed;

    public SportPassCar(int id,String marka, String model, float capacity, int power, boolean automaticGearbox, int basicPrice, int yearOfProduction, int numOfDoors, int numOfSeats, boolean chipTuning, boolean spoiler, int maxSpeed) {
        super(id, marka, model, capacity, power, automaticGearbox, basicPrice, yearOfProduction, numOfDoors, numOfSeats);
        this.chipTuning = chipTuning;
        this.spoiler = spoiler;
        this.maxSpeed = maxSpeed;
    }

    public double getPrice() {
        int ratio=1;
        if (isSpoiler()) {
            ratio*=1.05;
        }
        if (isChipTuning()) {
            ratio*=1.3;
        }
        if(getMaxSpeed()>280) {
            ratio*=1.2;
        }
        return ratio*super.getPrice();
    }

    public String getInfoCar() {
        String tmp = super.getInfoCar() + ", Chip Tuning: " + chipTuning+ ", Spoiler: " + spoiler
                + ", Maksymalna prędkość: " + maxSpeed;
        return tmp;
    }

    public boolean isChipTuning() {
        return chipTuning;
    }

    public boolean isSpoiler() {
        return spoiler;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setChipTuning(boolean chipTuning) {
        this.chipTuning = chipTuning;
    }

    public void setSpoiler(boolean spoiler) {
        this.spoiler = spoiler;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
