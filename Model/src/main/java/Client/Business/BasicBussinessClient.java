package Client.Business;

import vehicle.Vehicle;

public class BasicBussinessClient  extends BusinessClient{
    public BasicBussinessClient(int id, String address,String password, String login, String taxNumber, String nameCompany, String representativeName, String representativeTel) {
        super(id, address, password,  login, taxNumber, nameCompany, representativeName, representativeTel);
    }

    @Override
    public double getDiscount(Vehicle vehicle) {
        double ratio= 1;
        if(getLoyaltyPoints()>1000000) {
            ratio=0.96;
        } else if(getLoyaltyPoints()>5000000) {
            ratio=0.99;
        }
        return super.getDiscount(vehicle)*ratio;
    }
}
