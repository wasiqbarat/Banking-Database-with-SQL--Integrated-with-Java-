package dbproject.bank;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("firstMenu.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AmirKabir Bank");

        stage.setHeight(400);
        stage.getIcons().add(new Image("file:src\\main\\resources\\assets\\icon2.png"));
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}