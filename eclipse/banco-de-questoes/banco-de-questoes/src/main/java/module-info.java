module com.bancodequestoes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.bancodequestoes to javafx.fxml;
    exports com.bancodequestoes;
    exports com.bancodequestoes.controllers;
    opens com.bancodequestoes.controllers to javafx.fxml;
    exports com.bancodequestoes.application;
    opens com.bancodequestoes.application to javafx.fxml;
}