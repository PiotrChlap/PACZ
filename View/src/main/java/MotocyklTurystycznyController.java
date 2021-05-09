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

public class MotocyklTurystycznyController {
    public Button go_back;
    ObservableList<String> SkrzyniaBiegowLista = FXCollections
            .observableArrayList("Tak","Nie");
    ObservableList<String> TypNapeduLista = FXCollections
            .observableArrayList("Łańcuch","Wał Kardana","Pas napędowy");
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
    private ComboBox TypNapeduBox;
    @FXML
    private TextField PojemnoscBagaznika;
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    @FXML
    private void initialize(){
        SkrzyniaBiegowBox.setValue("Nie");
        SkrzyniaBiegowBox.setItems(SkrzyniaBiegowLista);

        TypNapeduBox.setValue("Łańcuch");
        TypNapeduBox.setItems(TypNapeduLista);
    }

    public void add_vechicle(ActionEvent actionEvent) throws SQLException {
        boolean tmp;
        if(SkrzyniaBiegowBox.getValue().equals("Tak")){
            tmp=true;
        } else {
            tmp=false;
        }

        String ask ="SELECT max(idx) FROM  (SELECT id_t as idx FROM special UNION ALL\n" +
                "SELECT id_t as idx FROM truck\n" +
                "UNION ALL\n" +
                "SELECT id_t as idx FROM  sportPassCar\n" +
                "UNION ALL\n" +
                "SELECT id_t as idx FROM premiumPassCar\n" +
                "UNION ALL\n" +
                "SELECT id_t as idx FROM familyPassCar\n" +
                "UNION ALL\n" +
                "SELECT id_t as idx FROM chopper\n" +
                "UNION ALL\n" +
                "SELECT id_t as idx FROM cross_M\n" +
                "UNION ALL\n" +
                "SELECT id_t as idx FROM sportMotorcycle\n" +
                "UNION ALL\n" +
                "SELECT id_t as idx FROM touristMotorcycle) as t";
        Statement pst1 = conn.createStatement();
        ResultSet set = pst1.executeQuery(ask);
        int data = 0;
        while (set.next()) {
            data = set.getInt(1);
        }

        String query = "INSERT INTO touristMotorcycle values(?,?,?,?,?,?, ?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, data + 1);
        pst.setString(2, marka.getText());
        pst.setString(3, model.getText());
        pst.setFloat(4, Float.parseFloat(pojemnosc.getText()));
        pst.setInt(5, Integer.parseInt(MocSilnika.getText()));
        pst.setBoolean(6, tmp);
        pst.setInt(7, Integer.parseInt(Cena.getText()));
        pst.setInt(8, Integer.parseInt(RokProdukcji.getText()));
        pst.setString(9,(String) TypNapeduBox.getValue());
        pst.setInt(10,Integer.parseInt(PojemnoscBagaznika.getText()));
        pst.executeUpdate();
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
