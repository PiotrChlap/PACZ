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

public class RodzinneAutoController {
    public Button go_back;
    ObservableList<String> SkrzyniaBiegowLista = FXCollections
            .observableArrayList("Tak","Nie");
    ObservableList<String> ZapasoweKoloLista = FXCollections
            .observableArrayList("Tak","Nie");

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
    private TextField LiczbaDrzwi;
    @FXML
    private TextField LiczbaSiedzen;
    @FXML
    private TextField TypAuta;
    @FXML
    private TextField IloscSiedzenDlaDziecka;
    @FXML
    private ComboBox ZapasoweKolo;

    @FXML
    private void initialize(){
        SkrzyniaBiegowBox.setValue("Nie");
        SkrzyniaBiegowBox.setItems(SkrzyniaBiegowLista);

        ZapasoweKolo.setValue("Nie");
        ZapasoweKolo.setItems(ZapasoweKoloLista);
    }

    public void add_vehicle(ActionEvent actionEvent) {
        System.out.println(PoleID.getText());
        System.out.println(marka.getText());
        System.out.println(model.getText());
        System.out.println(pojemnosc.getText());
        System.out.println(MocSilnika.getText());
        System.out.println(SkrzyniaBiegowBox.getValue());
        System.out.println(Cena.getText());
        System.out.println(RokProdukcji.getText());
        System.out.println(LiczbaDrzwi.getText());
        System.out.println(LiczbaSiedzen.getText());
        System.out.println(TypAuta.getText());
        System.out.println(IloscSiedzenDlaDziecka.getText());
        System.out.println(ZapasoweKolo.getValue());
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
