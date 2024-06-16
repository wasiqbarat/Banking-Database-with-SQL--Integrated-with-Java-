package dbproject.bank;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecentTransactions extends Controller {

    @FXML
    private TextField cardnumber;

    @FXML
    private TextField n;

    @FXML
    void search(ActionEvent event) throws Exception {
        checkIfEmpty(cardnumber);
        checkIfEmpty(n);

        int number;
        try {
            number = Integer.parseInt(n.getText());

        }catch (Exception e) {
            n.setText("");
            n.requestFocus();
            return;
        }

        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        con = Controller.connectDB();

        String sql1 = "select * from get_last_n_transactions(" +number+ ", '" + cardnumber.getText() + "');";
        p = con.prepareStatement(sql1);
        rs = p.executeQuery();

        // Printing ID, name, email of customers
        // of the SQL command above
        System.out.println("trackingcode\t\tsource\t\tdestination\t\tamount\t\ttype\t\ttime\t\tdata");

        // Condition check
        while (rs.next()) {

            String trackingcode = rs.getString("trackingcode");
            String source = rs.getString("source");
            String destination = rs.getString("destination");
            String amount = rs.getString("amount");
            String type = rs.getString("type");
            String time = rs.getString("time");
            String date = rs.getString("data");
            System.out.println(trackingcode + "\t\t\t\t\t" + source
                    + "\t\t\t\t" + destination+ "\t\t" +amount+ "\t\t" +type+ "\t\t" +time+ "\t\t" +date);
        }

    }
}
