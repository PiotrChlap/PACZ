import Rest.Controller;
import Rest.DAO;
import Rest.Place;
import Rest.admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    public Button quit;
    public Button log_in_admin;
    public Button log_in;
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField admin_pass;

    private Connection conn;
    private Place place;
    private Controller controller;

    @FXML
    public void initialize(){
        DAO dao = new DAO();
        conn = dao.connect();
        place = new Place();
        controller = new Controller(place);

    }

    public void log_in(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Klient.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Klient");
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) log_in.getScene().getWindow();
        stage.close();

    }

    public void log_admin(ActionEvent actionEvent) throws IOException, SQLException {
        String ask = "SELECT pass_admin FROM admin";
        Statement pst1 = conn.createStatement();
        ResultSet set = pst1.executeQuery(ask);
        String base_haslo="";
        while(set.next()){
            base_haslo = set.getString(1);
        }
        if(admin_pass.getText().equals(base_haslo)) {
            FXMLLoader  loader = new FXMLLoader(getClass().getResource("/Admin.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Administracja");
            stage.setScene(new Scene(root));
            AdminControll adminControll = loader.getController();
            adminControll.setAdmin(new admin(admin_pass.getText()));
            adminControll.setPlace(place);
            adminControll.setConn(conn);
            stage.show();
            stage = (Stage) log_in_admin.getScene().getWindow();
            stage.close();
        }


    }

}
