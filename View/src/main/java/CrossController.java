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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CrossController {
    public Button go_back;
    ObservableList<String> SkrzyniaBiegowLista = FXCollections
            .observableArrayList("Tak","Nie");
    ObservableList<String> TypNapeduLista = FXCollections
            .observableArrayList("Łańcuch","Wał Kardana","Pas napędowy");

    @FXML
    private TextField PoleID;
    @FXML
    private TextField marka;
    @FXML
    private TextField model;
    @FXML
    private TextField pojemnosc;
    @FXML
    private TextField MocSilnika;
    @FXML
    private ComboBox SkrzyniaBiegowBox;
    @FXML
    private TextField Cena;
    @FXML
    private TextField RokProdukcji;
    @FXML
    private ComboBox TypNapeduBox;
    @FXML
    private TextField MomentObrotowy;

    @FXML
    private void initialize(){
        SkrzyniaBiegowBox.setValue("Nie");
        SkrzyniaBiegowBox.setItems(SkrzyniaBiegowLista);

        TypNapeduBox.setValue("Łańcuch");
        TypNapeduBox.setItems(TypNapeduLista);
    }

    public void add_vechicle(ActionEvent actionEvent) {
        System.out.println(PoleID.getText());
        System.out.println(marka.getText());
        System.out.println(model.getText());
        System.out.println(pojemnosc.getText());
        System.out.println(MocSilnika.getText());
        System.out.println(SkrzyniaBiegowBox.getValue());
        System.out.println(Cena.getText());
        System.out.println(RokProdukcji.getText());
        System.out.println(TypNapeduBox.getValue());
        System.out.println(MomentObrotowy.getText());
    }

    public void go_back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/DodajPojazd.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Dodaj_Pojazd");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
        stage = (Stage) go_back.getScene().getWindow();
        stage.close();
    }
}
