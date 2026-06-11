package com.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Зам зөв эсэхийг хавтасны бүтцээсээ дахин нягтлаарай
        scene = new Scene(loadFXML("com/library/san"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        String finalPath = "/" + fxml + ".fxml";
        URL fxmlLocation = App.class.getResource(finalPath);

        // Файл олдохгүй бол тодорхой алдааны мэдээлэл хэвлэнэ
        if (fxmlLocation == null) {
            throw new IOException("Файл олдсонгүй! Замаа шалгана уу: " + finalPath);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}