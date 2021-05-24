import Client.Client;
import Rest.DataBaseMenager;
import Rest.Order;
import Rest.Place;
import Rest.Rent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vehicle.Car.*;
import vehicle.Motorcycle.*;
import vehicle.Vehicle;

import java.sql.*;
import java.time.LocalDate;

public class DodajZamowienieController {
    public Button go_back;
    ObservableList<String> dayList = FXCollections
            .observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
    ObservableList<String> monthList = FXCollections
            .observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
    ObservableList<String> avaibleCar = FXCollections
            .observableArrayList();
    private Client client;
    private Order order;
    private Place place;
    private Connection conn;
    private DataBaseMenager dataBaseMenager;
    @FXML
    private ComboBox car_id;
    @FXML
    private ComboBox day;
    @FXML
    private ComboBox month;
    @FXML
    private TextField year;
    @FXML
    private Text orders;

    public void setDataBaseMenager(DataBaseMenager dataBaseMenager) {
        this.dataBaseMenager = dataBaseMenager;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    private void initialize(){
        day.setValue("Dzien");
        day.setItems(dayList);

        month.setValue("Miesiac");
        month.setItems(monthList);
    }


    public void add_vehicle(ActionEvent actionEvent) throws SQLException {
        int data= dataBaseMenager.getMaxIDRent();
        Rent rent = new Rent(data+1, LocalDate.now(),LocalDate.of(Integer.parseInt(year.getText()),Integer.parseInt((String) month.getValue()),Integer.parseInt((String) day.getValue())),client.getController().handOverCar(Integer.parseInt((String) car_id.getValue())));
        order.addRent(rent);
        dataBaseMenager.addNewRent(data,rent,order,(String) car_id.getValue());
        String simpleName = client.getController().findCarRented(Integer.parseInt((String) car_id.getValue())).getClass().getSimpleName();
        String base = "";
        if(simpleName.equals("Truck")){
            base="truck";
        } else if (simpleName.equals("Special")) {
            base="special";
        } else if (simpleName.equals("SportPassCar")) {
            base="sportPassCar";
        } else if (simpleName.equals("PremiumPassCar")) {
            base="premiumPassCar";
        } else if (simpleName.equals("FamilyPassCar")) {
            base="familyPassCar";
        } else if (simpleName.equals("Chopper")) {
            base="chopper";
        } else if (simpleName.equals("Cross")) {
            base="cross_M";
        } else if (simpleName.equals("SportMotorcycle")) {
            base="sportMotorcycle";
        } else {
            base="touristMotorcycle";
        }
        dataBaseMenager.RentCarUpdate(base,(String) car_id.getValue());

        client.addLoyaltyPoints((int) (Math.round(rent.calculateCost(client))*10));
        orders.setText(order.getInfoOrder());

    }

    @FXML
    public void refresh(){
        avaibleCar.clear();
        for(Vehicle vehicle : place.getAvailbleCars()){
            avaibleCar.add(String.valueOf(vehicle.getCarId()));
        }
        car_id.setItems(avaibleCar);
    }

    public void go_back_button(ActionEvent actionEvent) {
        Stage stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }
}
