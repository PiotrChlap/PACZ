import Rest.DataBaseMenager;
import Rest.Place;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CiezarowkaController {
    public Button go_back;
    ObservableList<String> SkrzyniaBiegowLista = FXCollections
            .observableArrayList("Tak","Nie");
    private Place place;

    public void setPlace(Place place) {
        this.place = place;
    }
    @FXML
    private TextField PoleID;
    @FXML
    private TextField marka;
    @FXML
    private TextField model;
    @FXML
    private TextField pojemnosc;
    @FXML
    private TextField MocSilnika;
    @FXML
    private ComboBox SkrzyniaBiegowBox;
    @FXML
    private TextField Cena;
    @FXML
    private TextField RokProdukcji;
    @FXML
    private TextField LiczbaDrzwi;
    @FXML
    private TextField LiczbaSiedzen;
    @FXML
    private TextField MaksymalnaLadownosc;
    private Connection conn;
    private DataBaseMenager dataBaseMenager;

    public void setDataBaseMenager(DataBaseMenager dataBaseMenager) {
        this.dataBaseMenager = dataBaseMenager;
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    @FXML
    private void initialize(){
        SkrzyniaBiegowBox.setValue("Nie");
        SkrzyniaBiegowBox.setItems(SkrzyniaBiegowLista);
    }

    public void add_vechicle(ActionEvent actionEvent) throws SQLException {
        List<String> arg = new ArrayList<>();
        arg.add(marka.getText());
        arg.add(model.getText());
        arg.add(pojemnosc.getText());
        arg.add(MocSilnika.getText());
        arg.add((String) SkrzyniaBiegowBox.getValue());
        arg.add(Cena.getText());
        arg.add(RokProdukcji.getText());
        arg.add(LiczbaDrzwi.getText());
        arg.add(MaksymalnaLadownosc.getText());
        dataBaseMenager.addNewTruck(arg);
        dataBaseMenager.updatePlaceDataBase(place);
    }

    public void go_back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/DodajPojazd.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Dodaj_Pojazd");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }
}
