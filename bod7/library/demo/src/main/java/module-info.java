module com.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;

    opens com.library to javafx.fxml;
    opens com.library.controllers to javafx.fxml;
    opens com.library.models to javafx.base;

    exports com.library;
}