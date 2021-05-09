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
    private Connection conn;

    @FXML
    private GridPane avaibleCarPane;
    private GridPane copyPanel;
    private  boolean x =false;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void initialize() throws SQLException {
        copyPanel= avaibleCarPane;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setAdmin(Rest.admin admin) {
        this.admin = admin;
        System.out.println("Ustawiono");
    }

    public void add_vechicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DodajPojazd.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        DodajPojazdController dodajPojazdController = loader.getController();
        dodajPojazdController.setPlace(place);
        dodajPojazdController.setConn(conn);
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
        Parent root = (Parent) loader.load();
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

//        GridPane new_grid = new GridPane();
//        new_grid.getRowConstraints().add(avaibleCarPane.getRowConstraints().get(0));
//        avaibleCarPane.getChildren().clear();

        String [] tabela = {"truck","special","sportPassCar","premiumPassCar","familyPassCar","chopper","cross_M","sportMotorcycle","touristMotorcycle"};
        int [] tabela2 = {10,10,13,13,13,10,10,10,10};
        place.getAvailbleCars().clear();
        for(int i =0 ; i<9; i++){
            String ask = "SELECT * FROM " + tabela[i];
            Statement pst1 = conn.createStatement();
            ResultSet set = pst1.executeQuery(ask);
            int j =0;
            while(set.next()) {
                System.out.println(set.getString(2));
                String [] tmp = new String[tabela2[i]];
                for(int z=0; z<tabela2[i];z++) {
                    tmp[z]=set.getString(z+1);
                }
                if(tabela[i].equals("truck")){
                    place.addCar(new Truck(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                            Integer.parseInt(tmp[8]),Integer.parseInt(tmp[9])));
                } else if (tabela[i].equals("special")) {
                    place.addCar(new Special(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                            Integer.parseInt(tmp[8]),tmp[9]));
                } else if (tabela[i].equals("sportPassCar")) {
                    place.addCar(new SportPassCar(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                            Integer.parseInt(tmp[8]),Integer.parseInt(tmp[9]),Boolean.parseBoolean(tmp[10]),Boolean.parseBoolean(tmp[11]),Integer.parseInt(tmp[12])));
                } else if (tabela[i].equals("premiumPassCar")) {
                    place.addCar(new PremiumPassCar(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                            Integer.parseInt(tmp[8]),Integer.parseInt(tmp[9]),Boolean.parseBoolean(tmp[10]),Boolean.parseBoolean(tmp[11]),Boolean.parseBoolean(tmp[12])));
                } else if (tabela[i].equals("familyPassCar")) {
                    place.addCar(new FamilyPassCar(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                            Integer.parseInt(tmp[8]),Integer.parseInt(tmp[9]),typeFamilyCar.valueOf(tmp[10]),Integer.parseInt(tmp[11]),Boolean.parseBoolean(tmp[12])));
                } else if (tabela[i].equals("chopper")) {
                    place.addCar(new Chopper(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                            driveTypeMotorcycle.valueOf(tmp[8]),Float.parseFloat(tmp[9])));
                } else if (tabela[i].equals("cross_M")) {
                    place.addCar(new Cross(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                            driveTypeMotorcycle.valueOf(tmp[8]),Integer.parseInt(tmp[9])));
                } else if (tabela[i].equals("sportMotorcycle")) {
                    place.addCar(new SportMotorcycle(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                            driveTypeMotorcycle.valueOf(tmp[8]),Integer.parseInt(tmp[9])));
                } else {
                    place.addCar(new TouristMotorcycle(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                            driveTypeMotorcycle.valueOf(tmp[8]),Integer.parseInt(tmp[9])));
                }
            }
        }
        if(!x){
            x=true;
        } else {
            avaibleCarPane.getChildren().remove(avaibleCarPane.getCellBounds(0,1));
        }
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

        System.out.println();
    }

}
