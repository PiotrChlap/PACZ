package Client.Individual;

import Client.Client;
import vehicle.Vehicle;

public abstract class IndividualClient extends Client{
    private String firstName;
    private String surname;
    private String personalIdNum;

    public IndividualClient(int id, String address, String password, String login, String firstName, String surname, String personalIdNum) {
        super(id, address,password , login);
        this.firstName = firstName;
        this.surname = surname;
        this.personalIdNum = personalIdNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonalIdNum() {
        return personalIdNum;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPersonalIdNum(String personalIdNum) {
        this.personalIdNum = personalIdNum;
    }

    @Override
    public double getDiscount(Vehicle vehicle) {
        double ratio=1;
        if(getLoyaltyPoints()>1000000) {
            ratio=0.995;
        } else if(getLoyaltyPoints()>500000) {
            ratio=0.9995;
        }
        return super.getDiscount(vehicle)*ratio;
    }
    @Override
    public String getBasedInfo(){
        StringBuilder tmp = new StringBuilder();
        tmp.append(getFirstName());
        tmp.append(" ");
        tmp.append(getSurname());
        tmp.append("\n");
        tmp.append(getPersonalIdNum());
        tmp.append("\n");
        return tmp.toString()+super.getBasedInfo();
    }
}
