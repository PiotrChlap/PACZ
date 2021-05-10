import Client.Business.BasicBussinessClient;
import Client.Business.PremiumBusinessClient;
import Client.Client;
import Client.Individual.BasicIndClient;
import Client.Individual.DiamondIndClient;
import Client.Individual.SilverIndClient;
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
import java.sql.*;
import java.time.LocalDate;

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
    public void initialize() throws SQLException {
        DAO dao = new DAO();
        conn = dao.connect();
        place = new Place();
        controller = new Controller(place);
        LocalDate l1 = LocalDate.of(2021,5,9);
        LocalDate l2 = LocalDate.of(2021,6,7);
        String ask = "SELECT * FROM rent";
        Statement pst1 = conn.createStatement();
        ResultSet set = pst1.executeQuery(ask);
        String [] tabela = {"truck","special","sportPassCar","premiumPassCar","familyPassCar","chopper","cross_M","sportMotorcycle","touristMotorcycle"};
        while (set.next()){
            if(!set.getBoolean(5)){
                if(LocalDate.now().isAfter(LocalDate.parse(set.getString(3)))){
                    String query = "update rent set closed=true where id_r = "+ set.getInt(1);
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.executeUpdate();

                    String ask1 ="SELECT * FROM  (SELECT id_t as idx FROM special UNION ALL\n" +
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
                    Statement pst3 = conn.createStatement();
                    ResultSet set2 = pst3.executeQuery(ask1);
                    while (set2.next()) {
                        if(set2.getInt(1)==set.getInt(6)){
                            for(String string: tabela){
                                String query1 = "update "+string+" set rented=false where id_t = "+ set2.getInt(1);
                                PreparedStatement pst2 = conn.prepareStatement(query1);
                                pst2.executeUpdate();
                            }
                            break;
                        }
                    }
                }
            }
        }

    }

    public void log_in(ActionEvent actionEvent) throws IOException, SQLException {
        String ask = "SELECT * FROM client";
        Statement pst1 = conn.createStatement();
        ResultSet set = pst1.executeQuery(ask);
        String base_login;
        String base_haslo;
        while(set.next()){
            base_haslo = set.getString(3);
            base_login = set.getString(4);
            if(base_haslo.equals(pass.getText()) && base_login.equals(login.getText())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Klient.fxml"));
                Parent root = loader.load();
                KlientControll klientControll = loader.getController();
                klientControll.setConn(conn);
                klientControll.setPlace(place);
                klientControll.setController(controller);
                if(set.getInt(12)==1){
                    BasicBussinessClient basicBussinessClient = new BasicBussinessClient(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),
                            set.getString(6),set.getString(7),set.getString(8));
                    basicBussinessClient.addLoyaltyPoints(set.getInt(13));
                    klientControll.setClient(basicBussinessClient);
                } else if(set.getInt(12)==2){
                    PremiumBusinessClient premiumBusinessClient = new PremiumBusinessClient(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),
                            set.getString(6),set.getString(7),set.getString(8));
                    premiumBusinessClient.setLoyaltyPoints(set.getInt(13));
                    klientControll.setClient(premiumBusinessClient);
                } else if(set.getInt(12)==3){
                    BasicIndClient basicIndClient = new BasicIndClient(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(9),set.getString(10),
                            set.getString(11));
                    basicIndClient.setLoyaltyPoints(set.getInt(13));
                    klientControll.setClient(basicIndClient);
                } else if(set.getInt(12)==4){
                    SilverIndClient silverIndClient = new SilverIndClient(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(9),set.getString(10),
                            set.getString(11));
                    silverIndClient.setLoyaltyPoints(set.getInt(13));
                    klientControll.setClient(silverIndClient);
                } else {
                    DiamondIndClient diamondIndClient = new DiamondIndClient(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(9),set.getString(10),
                            set.getString(11));
                    diamondIndClient.setLoyaltyPoints(set.getInt(13));
                    klientControll.setClient(diamondIndClient);
                }

                Stage stage = new Stage();
                stage.setTitle("Klient");
                stage.setScene(new Scene(root));
                stage.show();
                stage = (Stage) log_in.getScene().getWindow();
                stage.close();
                break;
            }
        }

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
