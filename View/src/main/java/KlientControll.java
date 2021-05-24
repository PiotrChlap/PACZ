import Client.Client;
import Rest.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vehicle.Car.*;
import vehicle.Motorcycle.*;
import vehicle.Vehicle;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class KlientControll {
    @FXML
    private Button log_out;
    @FXML
    private TextField car_ID;
    @FXML
    private Text infoOrders;
    @FXML
    private GridPane listaAvaibleCar;
    @FXML
    private ComboBox num_zamowienia;

    ObservableList<String> listZamowienID = FXCollections
            .observableArrayList("");

    private Client client;
    private Place place;
    private Connection conn;
    private Controller controller;
    private DataBaseMenager dataBaseMenager;

    public void setDataBaseMenager(DataBaseMenager dataBaseMenager) {
        this.dataBaseMenager = dataBaseMenager;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }


    @FXML
    public void  refresh_num_zamowienia() throws SQLException, IOException {
        zaladuj();
        listZamowienID.clear();
        for(Order order: client.getListOfOrders()){
            listZamowienID.add(String.valueOf(order.getId()));
        }
        num_zamowienia.setItems(listZamowienID);
    }


    public void new_order(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DodajZamowienie.fxml"));
        Parent root = loader.load();
        DodajZamowienieController dodajZamowienieController = loader.getController();
        client.setController(controller);
        dodajZamowienieController.setClient(client);
        dodajZamowienieController.setConn(conn);
        int data = dataBaseMenager.getMaxIDOrder();
        zaladuj();

        Order order = new Order(data+1, LocalDate.now(),client);
        dodajZamowienieController.setOrder(order);
        dodajZamowienieController.setPlace(place);
        dodajZamowienieController.setDataBaseMenager(dataBaseMenager);
        client.addOrder(order);
        dataBaseMenager.addNewOrder(client,order);


        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void more_info(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/WiecejInformacji.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Dodaj_Pojazd");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void log_out(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) log_out.getScene().getWindow();
        stage.close();
    }

    public void zaladuj() throws IOException, SQLException {
        dataBaseMenager.updatePlaceDataBase(place);
        dataBaseMenager.updateOrders(client, place);
        infoOrders.setText(client.getInfoOrders());
    }

    public void create_invoice(ActionEvent actionEvent) throws IOException, SQLException {
        zaladuj();
        CreaterFile createrFile = new CreaterFile();

        Invoice invoice = new Invoice(1,client.findOrder(Integer.parseInt((String) num_zamowienia.getValue())).getSubmitDate().plusMonths(2),client.findOrder(Integer.parseInt((String) num_zamowienia.getValue())).
                getSubmitDate(),false,client.findOrder(Integer.parseInt((String) num_zamowienia.getValue())));
        createrFile.createFile(invoice,client.getId(), client.findOrder(Integer.parseInt((String) num_zamowienia.getValue())).getId());
    }

    public void fillLista() throws SQLException, IOException {
        zaladuj();
        listaAvaibleCar.getChildren().clear();
        listaAvaibleCar.setMinHeight(160.0);
        listaAvaibleCar.add(new Text("ID pojazdu"),0,0);
        listaAvaibleCar.add(new Text("Marka"),1,0);
        listaAvaibleCar.add(new Text("Model"),2,0);
        listaAvaibleCar.add(new Text("Pojemność"),3,0);
        listaAvaibleCar.add(new Text("Moc"),4,0);
        listaAvaibleCar.add(new Text("Automatyczna\nskrzynia\nbiegów"),5,0);
        listaAvaibleCar.add(new Text("Cena wypożyczenia"),6,0);
        listaAvaibleCar.add(new Text("Rok produkcji"),7,0);
        int i =1;
        for(Vehicle vehicle : place.getAvailbleCars()){
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.getCarId())),0,i);
            listaAvaibleCar.add(new Text(vehicle.getMarka()),1,i);
            listaAvaibleCar.add(new Text(vehicle.getModel()),2,i);
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.getCapacity())),3,i);
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.getPower())),4,i);
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.isAutomaticGearbox())),5,i);
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.getPrice())),6,i);
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.getYearOfProduction())),7,i);
            i++;
        }
    }

}
