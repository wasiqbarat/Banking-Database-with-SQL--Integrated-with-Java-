package dbproject.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class Paya extends Controller {
    @FXML
    private TextField amount;

    @FXML
    private TextField destination;

    @FXML
    private TextField source;

    @FXML
    private TextField destination1;

    @FXML
    private TextField source1;

    @FXML
    private Label status;

    @FXML
    void transferButtonPressed(ActionEvent event) throws Exception {
        checkIfEmpty(amount);
        checkIfEmpty(source);
        checkIfEmpty(destination);
        checkIfEmpty(destination1);
        checkIfEmpty(source1);


        try {
            Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            con = Controller.connectDB();

            StringBuilder sql1 = new StringBuilder();

            int money;
            try {
                money = Integer.parseInt(amount.getText());
            }catch (Exception e) {
                status.setText("invalid amount.");
                amount.setText("");
                amount.requestFocus();
                return;
            }

            sql1.append("EXEC transfer_funds ").append(source1.getText())
                    .append(", ").append(destination1.getText()).append(", '")
                    .append(source.getText()).append("', '").append(destination.getText()).append("',")
                    .append(money).append(";");

            p = con.prepareStatement(String.valueOf(sql1));
            p.execute();
            p.close();


            String sql = "select count(*) from transactionstable";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            rs.next();
            int tracking_Code = rs.getInt(1);
            rs.close();
            p.close();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Successful transaction");
            alert.setHeaderText("Tracking code for you transaction: " + tracking_Code);

            ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(ok);
            alert.showAndWait();

        }catch (Exception e) {
            e.printStackTrace();
            status.setText("Unsuccessful!!!! Please check you Input");
            return;
        }


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
