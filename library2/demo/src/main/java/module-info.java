module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // MySQL ашиглах тул заавал хэрэгтэй

    opens com.library to javafx.fxml;
    opens com.library.controllers to javafx.fxml;

    // Модель классуудыг JavaFX-ийн TableView-д нээх өгөх
    opens com.library.models to javafx.base, javafx.fxml;

    exports com.library;
}
