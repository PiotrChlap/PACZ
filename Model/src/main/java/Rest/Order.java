package Rest;

import Client.Client;
import vehicle.Vehicle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private LocalDate submitDate;
    private List<Rent> listOfRents = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
    private Client client;

    public Order(int id, LocalDate submitDate, Client client) {
        this.submitDate = submitDate;
        this.client = client;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public boolean addRent(Rent rent){
        listOfRents.add(rent);
        return true;
    }
    public int getAmountRentedCar(){
        int i =0;
        for(Rent rent: listOfRents) {
            i+=1;
        }
        return i;
    }

    public Rent findRent(int Id) {
        for (Rent rent : listOfRents) {
            if(rent.getRentId() == Id){
                return rent;
            }
        }
        return null;
    }
    public String getRentInfo(int Id){
        Rent rent = findRent(Id);
        if (rent==null){
            return null;
        }
        return rent.getInfo();
    }

    public int[] getIdRenst(){
        int [] tmp = new int[listOfRents.size()];
        for( int i=0; i<listOfRents.size(); i++) {
            tmp[i]=listOfRents.get(i).getRentId();
        }
        return tmp;
    }

    public Client getClient() {
        return client;
    }

    public String getInfoOrder() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("Data złożenia: ");
        tmp.append(getSubmitDate());
        tmp.append("\n");
        for( int i=0; i<listOfRents.size();i++) {
            tmp.append(i+1);
            tmp.append(". Wypożyczenie: \n");
            tmp.append(listOfRents.get(i).getInfo());
            tmp.append("\n");
            tmp.append("Koszt: ");
            tmp.append("\n");
            tmp.append(listOfRents.get(i).calculateCost(client));
            tmp.append("\n");
        }
        return tmp.toString();
    }

    public boolean closeRent(int Id){
        Rent rent = findRent(Id);
        if (rent==null){
            return false;
        }
        rent.setClosed();
        return true;
    }

    public boolean allRentsIsClosed() {
        for(Rent rent: listOfRents){
            if(!rent.isClosed()){
                return false;
            }
        }
        return true;
    }

    public double getTotalCost(){
        double cost=0.0;
        for(Rent rent : listOfRents){
            cost+=rent.calculateCost(client);
        }
        return cost;
    }
}
