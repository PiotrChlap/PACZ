import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajZamowienieController {
    public Button go_back;
    ObservableList<String> dayList = FXCollections
            .observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
    ObservableList<String> monthList = FXCollections
            .observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");


    @FXML
    private ComboBox car_id;
    @FXML
    private ComboBox day;
    @FXML
    private ComboBox month;
    @FXML
    private TextField year;
    @FXML
    private ComboBox car_id_delete;

    @FXML
    private void initialize(){
        day.setValue("Dzien");
        day.setItems(dayList);

        month.setValue("Miesiac");
        month.setItems(monthList);
    }

    public void go_back_button(ActionEvent actionEvent) {
        Stage stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }

    public void delete_car_button(ActionEvent actionEvent) {
    }

    public void add_vehicle(ActionEvent actionEvent) {
        System.out.println(day.getValue() + "." + month.getValue()+"."+year.getText());

    }

    public void finish(ActionEvent actionEvent) {
    }
}
