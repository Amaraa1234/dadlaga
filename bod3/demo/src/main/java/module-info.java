module com.example {
    // JavaFX-ийн үндсэн модулиудыг нээж өгөх (Энэ хэсэг дутуу байсан тул алдаа
    // заасан)
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.base;
    requires transitive javafx.graphics;

    opens com.example to javafx.fxml;

    exports com.example;
}
