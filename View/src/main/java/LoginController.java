import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public Button quit;
    public Button log_in_admin;
    public Button log_in;

    public void log_in(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Klient.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Klient");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) log_in.getScene().getWindow();
        stage.close();

    }

    public void log_admin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Admin.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Administracja");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) log_in_admin.getScene().getWindow();
        stage.close();
    }

}
