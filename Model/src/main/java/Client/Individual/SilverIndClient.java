package Client.Individual;

import vehicle.Vehicle;

public class SilverIndClient extends IndividualClient{
    public SilverIndClient(int id, String address, String password, String login, String firstName, String surname, String personalIdNum) {
        super(id, address, password, login, firstName, surname, personalIdNum);
    }
    @Override
    public double getDiscount(Vehicle vehicle) {
        double ratio = 1;
        if(vehicle.getClass().getSimpleName().equals("PremiumPassCar")){
            ratio=0.98;
        } else if(vehicle.getClass().getSimpleName().equals("Chopper")) {
            ratio=0.96;
        } else {
            ratio=0.95;
        }
        return super.getDiscount(vehicle)*ratio;
    }
}
