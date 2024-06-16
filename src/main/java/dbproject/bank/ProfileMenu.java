package dbproject.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileMenu extends Controller implements Initializable {


    @FXML
    private TextField abilities;

    @FXML
    private TextField userName;

    @FXML
    void changePasswordButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("changeUsernameOrPassword.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void previous(ActionEvent event) throws IOException {
        gotoMainMenu(event);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
