module com.example.lab11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.example.lab11 to javafx.fxml;
    opens com.example.lab11.controller to javafx.fxml;
    opens com.example.lab11.dto to javafx.fxml;

    exports com.example.lab11;
    exports com.example.lab11.controller;
    exports com.example.lab11.dto;
}