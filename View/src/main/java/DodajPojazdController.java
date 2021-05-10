import Rest.DataBaseMenager;
import Rest.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class DodajPojazdController {
    public Button go_back;

    private Place place;
    private Connection conn;
    private DataBaseMenager dataBaseMenager;

    public void setDataBaseMenager(DataBaseMenager dataBaseMenager) {
        this.dataBaseMenager = dataBaseMenager;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void lux_car(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LuksusoweAuto.fxml"));
        Parent root = loader.load();
        LuksusoweAutoController luksusoweAutoController = loader.getController();
        luksusoweAutoController.setPlace(place);
        luksusoweAutoController.setConn(conn);
        luksusoweAutoController.setDataBaseMenager(dataBaseMenager);
        Stage stage = new Stage();
        stage.setTitle("Luksusowe Auto");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void fam_car(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RodzinneAuto.fxml"));
        Parent root = loader.load();
        RodzinneAutoController rodzinneAutoController = loader.getController();
        rodzinneAutoController.setPlace(place);
        rodzinneAutoController.setConn(conn);
        rodzinneAutoController.setDataBaseMenager(dataBaseMenager);
        Stage stage = new Stage();
        stage.setTitle("Rodzinne Auto");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void fast_car(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SportoweAuto.fxml"));
        Parent root = loader.load();
        SportoweAutoController sportoweAutoController  = loader.getController();
        sportoweAutoController.setPlace(place);
        sportoweAutoController.setConn(conn);
        sportoweAutoController.setDataBaseMenager(dataBaseMenager);
        Stage stage = new Stage();
        stage.setTitle("Sportowe Auto");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void truck(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ciezarowka.fxml"));
        Parent root = loader.load();
        CiezarowkaController ciezarowkaController  = loader.getController();
        ciezarowkaController.setPlace(place);
        ciezarowkaController.setConn(conn);
        ciezarowkaController.setDataBaseMenager(dataBaseMenager);
        Stage stage = new Stage();
        stage.setTitle("Ciezarowka");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void special(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AutoSpecjalne.fxml"));
        Parent root = loader.load();
        AutoSpecjalneController autoSpecjalneController  = loader.getController();
        autoSpecjalneController.setPlace(place);
        autoSpecjalneController.setConn(conn);
        autoSpecjalneController.setDataBaseMenager(dataBaseMenager);
        Stage stage = new Stage();
        stage.setTitle("Auto Specjalne");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void fast_motor(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SportowyMotocykl.fxml"));
        Parent root = loader.load();
        SportowyMotocyklController sportowyMotocyklController  = loader.getController();
        sportowyMotocyklController.setPlace(place);
        sportowyMotocyklController.setConn(conn);
        sportowyMotocyklController.setDataBaseMenager(dataBaseMenager);
        Stage stage = new Stage();
        stage.setTitle("Sportowy Motocykl");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void tour_motor(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MotocyklTurystyczny.fxml"));
        Parent root = loader.load();
        MotocyklTurystycznyController motocyklTurystycznyController  = loader.getController();
        motocyklTurystycznyController.setPlace(place);
        motocyklTurystycznyController.setConn(conn);
        motocyklTurystycznyController.setDataBaseMenager(dataBaseMenager);
        Stage stage = new Stage();
        stage.setTitle("Motocykl Turystyczny");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void chopper(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chopper.fxml"));
        Parent root = loader.load();
        ChopperController chopperController  = loader.getController();
        chopperController.setPlace(place);
        chopperController.setConn(conn);
        chopperController.setDataBaseMenager(dataBaseMenager);
        Stage stage = new Stage();
        stage.setTitle("Chopper");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void cross(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cross.fxml"));
        Parent root = loader.load();
        CrossController crossController  = loader.getController();
        crossController.setPlace(place);
        crossController.setConn(conn);
        crossController.setDataBaseMenager(dataBaseMenager);
        Stage stage = new Stage();
        stage.setTitle("Cross");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void go_back_button(ActionEvent actionEvent) {
        Stage stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }
}
