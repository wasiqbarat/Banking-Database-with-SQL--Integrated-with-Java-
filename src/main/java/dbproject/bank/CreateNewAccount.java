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
import java.sql.SQLException;
import java.util.*;


public class CreateNewAccount extends Controller implements Initializable{
    private int accountId;

    @FXML
    private TextField accountID;

    @FXML
    private TextField balance;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField shebaNumber;

    @FXML
    private Label status;

    @FXML
    void saveButtonPressed(ActionEvent event) throws Exception {
        checkIfEmpty(accountID);
        checkIfEmpty(balance);
        checkIfEmpty(cardNumber);
        checkIfEmpty(shebaNumber);

        try {
            Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            con = Controller.connectDB();


            int balance1 = Integer.parseInt(balance.getText());

            StringBuilder sql1 = new StringBuilder();

            System.out.println(getLoggedUserName());

            sql1.append("insert into Accountstable values(").append(accountId)
                    .append(", '").append(cardNumber.getText()).append("', '")
                    .append(shebaNumber.getText()).append("', ").append(balance1).append(", '")
                    .append(getLoggedUserName()).append("');");

            p = con.prepareStatement(String.valueOf(sql1));
            p.execute();
            p.close();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        con = Controller.connectDB();
        String sql = "select count(*) from Accountstable";

        try {
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            rs.next();
            accountId = rs.getInt(1) + 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        accountID.setText(String.valueOf(accountId));
        accountID.setEditable(false);
    }
}
