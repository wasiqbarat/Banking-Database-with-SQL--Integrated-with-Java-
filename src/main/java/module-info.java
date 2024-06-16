module dbproject.bank {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;

    opens dbproject.bank to javafx.fxml;
    exports dbproject.bank;
}


