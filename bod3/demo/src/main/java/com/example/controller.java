package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Collections;
import java.util.Comparator;

public class controller {

    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student, Integer> statusColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> idColumn;
    @FXML
    private TableColumn<Student, Integer> scoreColumn;

    // FXML-ээс орж ирэх оролтын талбарууд
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputCode;
    @FXML
    private TextField inputScore;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private int currentId = 1; // Сурагчийн хувийн дугаарыг 1-ээс эхлүүлнэ

    @FXML
    public void initialize() {
        // Багануудыг холбох
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        // Анх нээгдэхэд хүснэгт хоосон байна (эсвэл хүсвэл жишээ өгөгдөл үлдээж болно)
        studentTableView.setItems(studentList);
    }

    // "Сурагч нэмэх" товч дарах үед ажиллах функц
    @FXML
    private void onAddStudent() {
        // 1. Гараас оруулсан текстийг авна
        String name = inputName.getText().trim();
        String code = inputCode.getText().trim();
        String scoreText = inputScore.getText().trim();

        // Хоосон утга оруулсан эсэхийг шалгах
        if (name.isEmpty() || code.isEmpty() || scoreText.isEmpty()) {
            return; // Аль нэг талбар хоосон бол юу ч хийхгүй
        }

        try {
            // 2. Оноог тоо руу хөрвүүлнэ
            int score = Integer.parseInt(scoreText);

            // 3. Шинэ сурагчийг жагсаалтад одоогийн ID-аар нэмээд, ID-г 1-ээр ахиулна
            studentList.add(new Student(currentId, name, code, score));
            currentId++;

            // 4. Бодлогын дүрмийн дагуу жагсаалтыг дахин эрэмбэлэх
            sortStudentList();

            // 5. Оролтын талбаруудыг цэвэрлэх
            inputName.clear();
            inputCode.clear();
            inputScore.clear();

        } catch (NumberFormatException e) {
            // Хэрэв оноон дээр тоо биш үсэг оруулбал алдааг алгасна
            System.out.println("Оноо хэсэгт зөвхөн бүхэл тоо оруулна уу!");
        }
    }

    // Эрэмбэлэх дэд функц
    private void sortStudentList() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // Оноогоор уруудах эрэмбээр (Ихээс бага руу)
                if (s1.getScore() != s2.getScore()) {
                    return Integer.compare(s2.getScore(), s1.getScore());
                }
                // Оноо тэнцвэл хувийн дугаар (№) багатайг нь түрүүлж (Өгсөх эрэмбэ)
                return Integer.compare(s1.getStatus(), s2.getStatus());
            }
        });
    }
}