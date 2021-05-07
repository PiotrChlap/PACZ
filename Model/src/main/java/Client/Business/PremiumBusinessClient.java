package Client.Business;

import vehicle.Vehicle;

public class PremiumBusinessClient extends BusinessClient {
    public PremiumBusinessClient(int id, String address,String password, String login, String taxNumber, String nameCompany, String representativeName, String representativeTel) {
        super(id, address, password,  login, taxNumber, nameCompany, representativeName, representativeTel);
    }

    @Override
    public double getDiscount(Vehicle vehicle) {
        double ratio=1;
        if(getLoyaltyPoints()>100000) {
            ratio=0.9;
        } else {
            ratio=0.95;
        }
        return super.getDiscount(vehicle)*ratio;
    }
}
