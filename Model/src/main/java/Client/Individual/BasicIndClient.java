package Client.Individual;

import vehicle.Vehicle;

public class BasicIndClient extends IndividualClient{
    public BasicIndClient(int id, String address, String password, String login, String firstName, String surname, String personalIdNum) {
        super(id, address, password,  login, firstName, surname, personalIdNum);
    }
    @Override
    public double getDiscount(Vehicle vehicle) {
        double ratio = 0.99;
        return super.getDiscount(vehicle)*ratio;
    }
}
