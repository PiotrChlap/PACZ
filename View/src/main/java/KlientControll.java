import Client.Client;
import Rest.Controller;
import Rest.Order;
import Rest.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class KlientControll {
    public Button log_out;
    public TextField car_ID;

    private Client client;
    private Place place;
    private Connection conn;
    private Controller controller;

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


    public void new_order(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DodajZamowienie.fxml"));
        Parent root = loader.load();
        DodajZamowienieController dodajZamowienieController = loader.getController();
        client.setController(controller);
        dodajZamowienieController.setClient(client);

        //Dodaj order
        client.addOrder(new Order());
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void more_info(ActionEvent actionEvent) throws IOException {
        System.out.println(car_ID.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/WiecejInformacji.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Dodaj_Pojazd");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void edit_order(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EdytujZamowienie.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Login");
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

    public void create_invoice(ActionEvent actionEvent) {
        System.out.println(client.getBasedInfo());
    }
}
