import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DodajPojazdController {
    public Button go_back;

    public void lux_car(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/LuksusoweAuto.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Luksusowe Auto");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void fam_car(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/RodzinneAuto.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Rodzinne Auto");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void fast_car(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/SportoweAuto.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Sportowe Auto");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void truck(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Ciezarowka.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ciezarowka");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void special(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AutoSpecjalne.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Auto Specjalne");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void fast_motor(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/SportowyMotocykl.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Sportowy Motocykl");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void tour_motor(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MotocyklTurystyczny.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Motocykl Turystyczny");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void chopper(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Chopper.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Chopper");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void cross(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Cross.fxml"));
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
