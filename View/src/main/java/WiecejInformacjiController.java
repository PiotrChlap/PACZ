import Rest.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vehicle.Car.*;
import vehicle.Motorcycle.Chopper;
import vehicle.Motorcycle.Cross;
import vehicle.Motorcycle.SportMotorcycle;
import vehicle.Motorcycle.TouristMotorcycle;
import vehicle.Vehicle;

public class WiecejInformacjiController {
    private Button quit;

    @FXML
    private GridPane infobase;

    @FXML
    private GridPane infoauto;

    @FXML
    private GridPane infomotor;

    private Place place;
    private int Id;

    public void setId(int id) {
        Id = id;
    }

    @FXML
    public void initialize(){

    }

    public void quit(ActionEvent actionEvent) {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @FXML
    public void use(){
        Vehicle vehicle=place.getVehicle(Id);
        if(place.getVehicle(Id).getClass().getSimpleName().equals("Truck")){
            infoauto.add(new Text(String.valueOf(((Truck)vehicle).getNumOfDoors())),1,0);
            infoauto.add(new Text(String.valueOf(((Truck)vehicle).getMaximumLoad())),1,10);
        } else if(place.getVehicle(Id).getClass().getSimpleName().equals("Special")){
            infoauto.add(new Text(String.valueOf(((Special)vehicle).getNumOfDoors())),1,0);
            infoauto.add(new Text(((Special)vehicle).getType()),1,5);
        } else if(place.getVehicle(Id).getClass().getSimpleName().equals("SportPassCar")) {
            infoauto.add(new Text(String.valueOf(((SportPassCar) vehicle).getNumOfDoors())), 1, 0);
            infoauto.add(new Text(String.valueOf(((SportPassCar) vehicle).getNumOfSeats())), 1, 1);
            infoauto.add(new Text(String.valueOf(((SportPassCar) vehicle).isChipTuning())), 1, 8);
            infoauto.add(new Text(String.valueOf(((SportPassCar) vehicle).isSpoiler())), 1, 9);
            infoauto.add(new Text(String.valueOf(((SportPassCar) vehicle).getMaxSpeed())), 1, 11);
        } else if(place.getVehicle(Id).getClass().getSimpleName().equals("PremiumPassCar")) {
            infoauto.add(new Text(String.valueOf(((PremiumPassCar) vehicle).getNumOfDoors())), 1, 0);
            infoauto.add(new Text(String.valueOf(((PremiumPassCar) vehicle).getNumOfSeats())), 1, 1);
            infoauto.add(new Text(String.valueOf(((PremiumPassCar) vehicle).isGlassRoof())), 1, 3);
            infoauto.add(new Text(String.valueOf(((PremiumPassCar) vehicle).isAutopilot())), 1, 2);
            infoauto.add(new Text(String.valueOf(((PremiumPassCar) vehicle).isBar())), 1, 4);
        } else if(place.getVehicle(Id).getClass().getSimpleName().equals("FamilyPassCar")) {
            infoauto.add(new Text(String.valueOf(((FamilyPassCar) vehicle).getNumOfDoors())), 1, 0);
            infoauto.add(new Text(String.valueOf(((FamilyPassCar) vehicle).getNumOfSeats())), 1, 1);
            infoauto.add(new Text(String.valueOf(((FamilyPassCar) vehicle).getType())), 1, 5);
            infoauto.add(new Text(String.valueOf(((FamilyPassCar) vehicle).getSeatChild())), 1, 6);
            infoauto.add(new Text(String.valueOf(((FamilyPassCar) vehicle).isSpareWheel())), 1, 7);
        } else if(place.getVehicle(Id).getClass().getSimpleName().equals("Chopper")) {
            infomotor.add(new Text(String.valueOf(((Chopper) vehicle).getDriveType())), 1, 0);
            infomotor.add(new Text(String.valueOf(((Chopper) vehicle).getLength())), 1, 3);
        } else if(place.getVehicle(Id).getClass().getSimpleName().equals("Cross")) {
            infomotor.add(new Text(String.valueOf(((Cross) vehicle).getDriveType())), 1, 0);
            infomotor.add(new Text(String.valueOf(((Cross) vehicle).getTorque())), 1, 4);
        } else if(place.getVehicle(Id).getClass().getSimpleName().equals("SportMotorcycle")) {
            infomotor.add(new Text(String.valueOf(((SportMotorcycle) vehicle).getDriveType())), 1, 0);
            infomotor.add(new Text(String.valueOf(((SportMotorcycle) vehicle).getMaxSpeed())), 1, 1);
        } else if(place.getVehicle(Id).getClass().getSimpleName().equals("TouristMotorcycle")) {
            infomotor.add(new Text(String.valueOf(((TouristMotorcycle) vehicle).getDriveType())), 1, 0);
            infomotor.add(new Text(String.valueOf(((TouristMotorcycle) vehicle).getBootCapacity())), 1, 2);
        }
        infobase.add(new Text(String.valueOf(vehicle.getCarId())),1,0);
        infobase.add(new Text(vehicle.getMarka()),1,1);
        infobase.add(new Text(vehicle.getModel()),1,2);
        infobase.add(new Text(String.valueOf(vehicle.getCapacity())),1,3);
        infobase.add(new Text(String.valueOf(vehicle.getPower())),1,4);
        infobase.add(new Text(String.valueOf(vehicle.isAutomaticGearbox())),1,5);
        infobase.add(new Text(String.valueOf(vehicle.getPrice())),1,6);
        infobase.add(new Text(String.valueOf(vehicle.getYearOfProduction())),1,7);


    }
}
