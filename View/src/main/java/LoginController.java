import Client.Client;
import Rest.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    private Button quit;
    @FXML
    private Button log_in_admin;
    @FXML
    private Button log_in;
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField admin_pass;

    private Connection conn;
    private Place place;
    private Controller controller;
    private DataBaseMenager dataBaseMenager;

    @FXML
    public void initialize() throws SQLException {
        place = new Place();
        controller = new Controller(place);
        dataBaseMenager= new DataBaseMenager();
        dataBaseMenager.checkEndedRent();
    }

    public void log_in(ActionEvent actionEvent) throws IOException, SQLException {
        Client client = dataBaseMenager.getClientDataBase(login.getText(),pass.getText());
        if(client != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Klient.fxml"));
            Parent root = loader.load();
            KlientControll klientControll = loader.getController();
            klientControll.setConn(conn);
            klientControll.setPlace(place);
            klientControll.setController(controller);
            klientControll.setClient(client);
            klientControll.setDataBaseMenager(dataBaseMenager);
            Stage stage = new Stage();
            stage.setTitle("Klient");
            stage.setScene(new Scene(root));
            stage.show();
            stage = (Stage) log_in.getScene().getWindow();
            stage.close();
        }


    }

    public void log_admin(ActionEvent actionEvent) throws IOException, SQLException {
        admin admin = dataBaseMenager.getAdminBase(admin_pass.getText());
        if(admin != null){
            FXMLLoader  loader = new FXMLLoader(getClass().getResource("/Admin.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Administracja");
            stage.setScene(new Scene(root));
            AdminControll adminControll = loader.getController();
            adminControll.setAdmin(admin);
            adminControll.setPlace(place);
            adminControll.setDataBaseMenager(dataBaseMenager);
            stage.show();
            stage = (Stage) log_in_admin.getScene().getWindow();
            stage.close();
        }
    }

}
