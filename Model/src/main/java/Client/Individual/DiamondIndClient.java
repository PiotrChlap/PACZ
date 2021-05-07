package Client.Individual;

import vehicle.Vehicle;

public class DiamondIndClient extends IndividualClient{
    public DiamondIndClient(int id, String address, String password, String login, String firstName, String surname, String personalIdNum) {
        super(id, address, password,  login, firstName, surname, personalIdNum);
    }

    @Override
    public double getDiscount(Vehicle vehicle) {
        double ratio = 1;
        if(vehicle.getClass().getSimpleName().equals("SportPassCar")){
            ratio=0.95;
        } else {
            ratio=0.90;
        }
        return super.getDiscount(vehicle)*ratio;
    }
}
