package dbproject.bank;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainMenu extends Controller{

    @FXML
    void createNewAccount(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("CreateNewAccount.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("AmirKabir Bank");

        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cardToCard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("CardToCard.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("AmirKabir Bank");
        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void paya(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Paya.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        Scene scene = new Scene(root);
        stage.setTitle("AmirKabir Bank");
        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void satna(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Satna.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("AmirKabir Bank");
        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void transactionValidation(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("TransactionValidation.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("AmirKabir Bank");
        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void recentTransactions(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("RecentTransactions.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        Scene scene = new Scene(root);
        stage.setTitle("AmirKabir Bank");
        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));

        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void LogOutButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("firstMenu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("AmirKabir Bank");
        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));
        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(550);
        stage.setResizable(false);
        stage.show();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void exitButtonPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to exit application?");

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yes) {
            Platform.exit();
        }
    }

}