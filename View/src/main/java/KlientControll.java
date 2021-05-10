import Client.Client;
import Rest.*;
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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vehicle.Car.*;
import vehicle.Motorcycle.*;
import vehicle.Vehicle;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class KlientControll {
    public Button log_out;
    public TextField car_ID;
    @FXML
    public Text infoOrders;
    @FXML
    public GridPane listaAvaibleCar;
    @FXML
    public ComboBox num_zamowienia;

    ObservableList<String> listZamowienID = FXCollections
            .observableArrayList("");



    private Client client;
    private Place place;
    private Connection conn;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }


    @FXML
    public void  refresh_num_zamowienia() throws SQLException, IOException {
        zaladuj();
        listZamowienID.clear();
        System.out.println("??????????????????");
        for(Order order: client.getListOfOrders()){
            listZamowienID.add(String.valueOf(order.getId()));
        }
        num_zamowienia.setItems(listZamowienID);
    }


    public void new_order(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DodajZamowienie.fxml"));
        Parent root = loader.load();
        DodajZamowienieController dodajZamowienieController = loader.getController();
        client.setController(controller);
        dodajZamowienieController.setClient(client);
        dodajZamowienieController.setConn(conn);
        String ask = "SELECT max(id_o) FROM order_o ";
        Statement pst1 = conn.createStatement();
        ResultSet set = pst1.executeQuery(ask);
        int data=1;
        while (set.next()){
            data=set.getInt(1);
        }
        zaladuj();
        Order order = new Order(data+1, LocalDate.now(),client);
        dodajZamowienieController.setOrder(order);
        dodajZamowienieController.setPlace(place);
        client.addOrder(order);
        String query = "INSERT INTO order_o values(?,?,?)";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, order.getId());
        pst.setString(2, order.getSubmitDate().toString());
        pst.setInt(3, client.getId());
        pst.executeUpdate();
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void more_info(ActionEvent actionEvent) throws IOException {
        System.out.println(car_ID.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/WiecejInformacji.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Dodaj_Pojazd");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void edit_order(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EdytujZamowienie.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Login");
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
    public void zaladuj() throws IOException, SQLException {

        String [] tabela = {"truck","special","sportPassCar","premiumPassCar","familyPassCar","chopper","cross_M","sportMotorcycle","touristMotorcycle"};
        int [] tabela2 = {10,10,13,13,13,10,10,10,10};
        place.getAvailbleCars().clear();
        place.getRentedCars().clear();
        for(int i =0 ; i<9; i++){
            String ask = "SELECT * FROM " + tabela[i];
            Statement pst1 = conn.createStatement();
            ResultSet set = pst1.executeQuery(ask);
            int j =0;
            while(set.next()) {
                String [] tmp = new String[tabela2[i]+1];
                for(int z=0; z<tabela2[i]+1;z++) {
                    tmp[z]=set.getString(z+1);
                }
                if(!set.getBoolean(tmp.length)){
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
                else {
                    if(tabela[i].equals("truck")){
                        place.addCarRented(new Truck(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                                Integer.parseInt(tmp[8]),Integer.parseInt(tmp[9])));
                    } else if (tabela[i].equals("special")) {
                        place.addCarRented(new Special(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                                Integer.parseInt(tmp[8]),tmp[9]));
                    } else if (tabela[i].equals("sportPassCar")) {
                        place.addCarRented(new SportPassCar(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                                Integer.parseInt(tmp[8]),Integer.parseInt(tmp[9]),Boolean.parseBoolean(tmp[10]),Boolean.parseBoolean(tmp[11]),Integer.parseInt(tmp[12])));
                    } else if (tabela[i].equals("premiumPassCar")) {
                        place.addCarRented(new PremiumPassCar(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                                Integer.parseInt(tmp[8]),Integer.parseInt(tmp[9]),Boolean.parseBoolean(tmp[10]),Boolean.parseBoolean(tmp[11]),Boolean.parseBoolean(tmp[12])));
                    } else if (tabela[i].equals("familyPassCar")) {
                        place.addCarRented(new FamilyPassCar(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                                Integer.parseInt(tmp[8]),Integer.parseInt(tmp[9]),typeFamilyCar.valueOf(tmp[10]),Integer.parseInt(tmp[11]),Boolean.parseBoolean(tmp[12])));
                    } else if (tabela[i].equals("chopper")) {
                        place.addCarRented(new Chopper(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                                driveTypeMotorcycle.valueOf(tmp[8]),Float.parseFloat(tmp[9])));
                    } else if (tabela[i].equals("cross_M")) {
                        place.addCarRented(new Cross(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                                driveTypeMotorcycle.valueOf(tmp[8]),Integer.parseInt(tmp[9])));
                    } else if (tabela[i].equals("sportMotorcycle")) {
                        place.addCarRented(new SportMotorcycle(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                                driveTypeMotorcycle.valueOf(tmp[8]),Integer.parseInt(tmp[9])));
                    } else {
                        place.addCarRented(new TouristMotorcycle(Integer.parseInt(tmp[0]),tmp[1],tmp[2],Float.parseFloat(tmp[3]),Integer.parseInt(tmp[4]),Boolean.parseBoolean(tmp[5]),Integer.parseInt(tmp[6]),Integer.parseInt(tmp[7]),
                                driveTypeMotorcycle.valueOf(tmp[8]),Integer.parseInt(tmp[9])));
                    }
                }

            }
        }

        String ask = "SELECT * FROM order_o where id_c=" +client.getId();
        Statement pst1 = conn.createStatement();
        ResultSet set = pst1.executeQuery(ask);
        client.getListOfOrders().clear();
        while(set.next()) {
            Order order =new Order(set.getInt(1),LocalDate.parse(set.getString(2)),client);
            client.addOrder(order);
            String ask2 = "SELECT * FROM rent where id_c=" +set.getInt(1);
            Statement pst2 = conn.createStatement();
            ResultSet set2 = pst2.executeQuery(ask2);
            while (set2.next()){
                order.addRent(new Rent(set2.getInt(1),LocalDate.parse(set2.getString(2)),LocalDate.parse(set2.getString(3)),place.getVehicle(set2.getInt(6))));
            }
        }
        infoOrders.setText(client.getInfoOrders());
    }
    public void create_invoice(ActionEvent actionEvent) throws IOException, SQLException {
        zaladuj();
        CreaterFile createrFile = new CreaterFile();

        Invoice invoice = new Invoice(1,client.findOrder(Integer.parseInt((String) num_zamowienia.getValue())).getSubmitDate().plusMonths(2),client.findOrder(Integer.parseInt((String) num_zamowienia.getValue())).
                getSubmitDate(),false,client.findOrder(Integer.parseInt((String) num_zamowienia.getValue())));
        createrFile.createFile(invoice,client.getId(), client.findOrder(Integer.parseInt((String) num_zamowienia.getValue())).getId());
    }

    public void fillLista() throws SQLException, IOException {
        zaladuj();
        listaAvaibleCar.getChildren().clear();
        listaAvaibleCar.setMinHeight(160.0);
        listaAvaibleCar.add(new Text("ID pojazdu"),0,0);
        listaAvaibleCar.add(new Text("Marka"),1,0);
        listaAvaibleCar.add(new Text("Model"),2,0);
        listaAvaibleCar.add(new Text("Pojemność"),3,0);
        listaAvaibleCar.add(new Text("Moc"),4,0);
        listaAvaibleCar.add(new Text("Automatyczna\nskrzynia\nbiegów"),5,0);
        listaAvaibleCar.add(new Text("Cena wypożyczenia"),6,0);
        listaAvaibleCar.add(new Text("Rok produkcji"),7,0);
        int i =1;
        for(Vehicle vehicle : place.getAvailbleCars()){
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.getCarId())),0,i);
            listaAvaibleCar.add(new Text(vehicle.getMarka()),1,i);
            listaAvaibleCar.add(new Text(vehicle.getModel()),2,i);
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.getCapacity())),3,i);
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.getPower())),4,i);
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.isAutomaticGearbox())),5,i);
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.getPrice())),6,i);
            listaAvaibleCar.add(new Text(String.valueOf(vehicle.getYearOfProduction())),7,i);
            i++;
        }
    }

}
