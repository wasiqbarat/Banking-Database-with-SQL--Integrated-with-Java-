package dbproject.bank;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class Controller {
    private static String loggedUserName = "";


    public static String getLoggedUserName() {
        return loggedUserName;
    }

    public static void setLoggedUserName(String loggedUserName) {
        Controller.loggedUserName = loggedUserName;
    }

    public static Connection connectDB() {
        Connection connection = null;
        String url = "jdbc:sqlserver://WASIQ_BARAT:1433;DatabaseName=BankDB;encrypt=true;trustServerCertificate=true;integratedSecurity=true;" +
                "allowMultiQueries=true";

        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        } catch (Exception e) {
            System.out.println("1");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;

    }

    public void gotoMainMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainMenu.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("AmirKabir Bank");

        stage.setScene(scene);

        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.show();
    }



    void checkIfEmpty(TextField textField) throws Exception {
        if (textField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information missed");
            alert.setHeaderText("Please fill the required text field.");

            ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(ok);
            Optional<ButtonType> result = alert.showAndWait();
            textField.requestFocus();
            Exception IOException = new Exception("Field is empty!");
            throw IOException;
        }
    }

}

