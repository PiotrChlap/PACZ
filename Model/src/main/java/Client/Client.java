package Client;

import Rest.Controller;
import Rest.Order;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public abstract class  Client {
    private int id;
    private String address;
    private int loyaltyPoints=0;
    private List<Order> listOfOrders=new ArrayList<>();
    private Controller controller;
    private String password;
    private String login;

    public Client(int id, String address, String password, String login) {
        this.id = id;
        this.address = address;
        this.password=password;
        this.login=login;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getId() {
        return id;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    public void addLoyaltyPoints(int loyaltyPoints){
        this.loyaltyPoints+=loyaltyPoints;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfoOrders() {
        StringBuilder tmp= new StringBuilder();
        if(listOfOrders.size()==0){
            tmp.append("Brak aktualnych zamówień");
        }
        else {
            for (int i = 0; i < listOfOrders.size(); i++) {
                tmp.append(i + 1);
                tmp.append(". Zamówienie \n");
                tmp.append(listOfOrders.get(i).getInfoOrder());
            }
        }
        return tmp.toString();
    }

    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    public Controller getController() {
        return controller;
    }

    public Order findOrder(int id) {
        for(Order order: listOfOrders) {
            if(order.getId()==id){
                return order;
            }
        }
        return null;
    }

    public boolean addOrder(Order order){
        listOfOrders.add(order);
        return true;
    }

    public boolean closeOrder(int id) {
        Order order= findOrder(id);
        if(order.allRentsIsClosed()){
            listOfOrders.remove(listOfOrders.indexOf(order));
            return true;
        }
        return false;
    }


    public double getDiscount(Vehicle vehicle) {
        return 1;

    }

    public String getBasedInfo(){
        StringBuilder tmp = new StringBuilder();
        tmp.append("Id: " );
        tmp.append(getId());
        tmp.append("\n");
        return tmp.toString();
    }
}
