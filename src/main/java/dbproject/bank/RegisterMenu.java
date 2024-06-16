package dbproject.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterMenu extends Controller {
    @FXML
    private Label status;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField nationalID;

    @FXML
    private PasswordField passwordAuthentication;

    @FXML
    private PasswordField passwordAuthentication1;

    @FXML
    private TextField userNameAuthentication;

    @FXML
    void cancelButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("firstMenu.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("AmirKabir Bank");

        stage.setScene(scene);

        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.show();


        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void saveButtonPressed(ActionEvent event) throws Exception {
        checkIfEmpty(nationalID);
        checkIfEmpty(firstName);
        checkIfEmpty(lastName);
        checkIfEmpty(userNameAuthentication);
        checkIfEmpty(passwordAuthentication);
        checkIfEmpty(passwordAuthentication1);


        if (!passwordAuthentication.getText().equals(passwordAuthentication1.getText())) {
            passwordAuthentication.setText("");
            passwordAuthentication.requestFocus();
            passwordAuthentication1.setText("");
        }

        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String nationalId = nationalID.getText();
        String username = userNameAuthentication.getText();
        if (nationalId.toCharArray().length != 10) {
            status.setText("National ID should be 10 Characters");
            nationalID.setText("");
            nationalID.requestFocus();
            return;
        }

        String password = passwordAuthentication.getText();
        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));

        try {
            Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            con = Controller.connectDB();
            String sql = "select count(*) from Userstable";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            rs.next();
            int user_id = rs.getInt(1) + 1;
            rs.close();
            p.close();


            StringBuilder sql1 = new StringBuilder();
            sql1.append("insert into userstable values('").append(username).append("', '").append(firstname).append("', '")
                    .append(lastname).append("', '").append(nationalId).append("', '").
                    append(generatedSecuredPasswordHash).append("');");

            p = con.prepareStatement(String.valueOf(sql1));
            p.execute();
            p.close();

        }catch (Exception e) {
            e.printStackTrace();
            status.setText("Unsuccessful!!!! Please check you Input");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("firstMenu.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("AmirKabir Bank");

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.show();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }


}
