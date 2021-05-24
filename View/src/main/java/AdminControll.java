import Rest.DataBaseMenager;
import Rest.Place;
import Rest.admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vehicle.Car.*;
import vehicle.Motorcycle.*;
import vehicle.Vehicle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminControll {
    public Button More_info;
    public TextField car_ID;
    public Button log_out;
    private Rest.admin admin;
    private Place place;
    @FXML
    private GridPane avaibleCarPane;
    @FXML
    private GridPane rentedCarPane;
    private DataBaseMenager dataBaseMenager;

    public void setDataBaseMenager(DataBaseMenager dataBaseMenager) {
        this.dataBaseMenager = dataBaseMenager;
    }


    public void setPlace(Place place) {
        this.place = place;
    }

    public void setAdmin(Rest.admin admin) {
        this.admin = admin;
    }

    public void add_vechicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DodajPojazd.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        DodajPojazdController dodajPojazdController = loader.getController();
        dodajPojazdController.setPlace(place);
        dodajPojazdController.setDataBaseMenager(dataBaseMenager);
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

    public void more_info(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WiecejInformacji.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        WiecejInformacjiController wiecejInformacjiController = loader.getController();
        wiecejInformacjiController.setId(Integer.parseInt(car_ID.getText()));
        wiecejInformacjiController.setPlace(place);
        stage.setTitle("Dodaj_Pojazd");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void zaladuj(ActionEvent actionEvent) throws IOException, SQLException {
        dataBaseMenager.updatePlaceDataBase(place);
        int i =1;
        for(Vehicle vehicle:place.getAvailbleCars()){

            avaibleCarPane.add(new Text(String.valueOf(vehicle.getCarId())),0,i);
            avaibleCarPane.add(new Text(vehicle.getMarka()),1,i);
            avaibleCarPane.add(new Text(vehicle.getModel()),2,i);
            avaibleCarPane.add(new Text(String.valueOf(vehicle.getCapacity())),3,i);
            avaibleCarPane.add(new Text(String.valueOf(vehicle.getPower())),4,i);
            avaibleCarPane.add(new Text(String.valueOf(vehicle.isAutomaticGearbox())),5,i);
            avaibleCarPane.add(new Text(String.valueOf(vehicle.getPrice())),6,i);
            avaibleCarPane.add(new Text(String.valueOf(vehicle.getYearOfProduction())),7,i);
            i+=1;
        }
        for(Vehicle vehicle:place.getRentedCars()){

            rentedCarPane.add(new Text(String.valueOf(vehicle.getCarId())),0,i);
            rentedCarPane.add(new Text(vehicle.getMarka()),1,i);
            rentedCarPane.add(new Text(vehicle.getModel()),2,i);
            rentedCarPane.add(new Text(String.valueOf(vehicle.getCapacity())),3,i);
            rentedCarPane.add(new Text(String.valueOf(vehicle.getPower())),4,i);
            rentedCarPane.add(new Text(String.valueOf(vehicle.isAutomaticGearbox())),5,i);
            rentedCarPane.add(new Text(String.valueOf(vehicle.getPrice())),6,i);
            rentedCarPane.add(new Text(String.valueOf(vehicle.getYearOfProduction())),7,i);
            i+=1;
        }

    }

}
