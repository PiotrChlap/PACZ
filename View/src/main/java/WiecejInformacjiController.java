import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WiecejInformacjiController {
    public Button quit;

    public void quit(ActionEvent actionEvent) {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }
}
