package Client.Business;

import Client.Client;
import vehicle.Vehicle;

 public abstract class BusinessClient extends Client {
    private String taxNumber;
    private String nameCompany;
    private String representativeName;
    private String representativeTel;


    public BusinessClient(int id, String address,String password, String login, String taxNumber, String nameCompany, String representativeName, String representativeTel) {
        super(id, address, password,  login);
        this.taxNumber = taxNumber;
        this.nameCompany = nameCompany;
        this.representativeName = representativeName;
        this.representativeTel = representativeTel;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public String getRepresentativeTel() {
        return representativeTel;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public void setRepresentativeTel(String representativeTel) {
        this.representativeTel = representativeTel;
    }

    @Override
    public double getDiscount(Vehicle vehicle) {
        double ratio = 1;
        if(vehicle.getClass().getSimpleName().equals("Special")) {
            ratio = 0.95;
        } else if (vehicle.getClass().getSimpleName().equals("Truck")){
            ratio=0.9;
        }
        return super.getDiscount(vehicle)*ratio;
    }

    @Override
    public String getBasedInfo(){
        StringBuilder tmp = new StringBuilder();
        tmp.append(nameCompany);
        tmp.append("\n");
        tmp.append(getTaxNumber());
        tmp.append("\n");
        return tmp.toString()+super.getBasedInfo();
    }
}
