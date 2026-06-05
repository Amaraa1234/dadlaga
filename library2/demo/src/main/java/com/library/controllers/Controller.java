package com.library.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.library.models.Book;
import com.library.database.DBConnection;

public class Controller {

    @FXML
    private Button btnAddBook;

    @FXML
    private TextField txtAuthor;
    @FXML
    private TextField txtBookName;
    @FXML
    private TextField txtBookSearch;
    @FXML
    private TextField txtIsbn;
    @FXML
    private TextField txtQuantity;

    @FXML
    private TableView<Book> tableBook;
    @FXML
    private TableColumn<Book, Integer> colId;
    @FXML
    private TableColumn<Book, String> colTitle;
    @FXML
    private TableColumn<Book, String> colAuthor;
    @FXML
    private TableColumn<Book, String> colIsbn;
    @FXML
    private TableColumn<Book, Integer> colQuantity;

    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (DBConnection.getConnection() != null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Holbogdson baina XD");
            alert.showAndWait();
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadBooksFromDatabase();
    }

    @FXML
    void onAddBookClick(ActionEvent event) {
        // TextField-үүдээс өгөгдөл унших
        String title = txtBookName.getText();
        String author = txtAuthor.getText();
        String isbn = txtIsbn.getText();
        String qtyStr = txtQuantity.getText();

        // Аль нэг талбар хоосон бол алдаа заах
        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || qtyStr.isEmpty()) {
            showAlert("Алдаа", "Бүх талбарыг бөглөнө үү!", Alert.AlertType.ERROR);
            return;
        }

        // ӨС-н аль хүснэгтийн, аль field-д бичлэг нэмэх query
        // Field нэр яг таарах ёстой!!!
        String insertQuery = "INSERT INTO book (title, author, isbn, quantity, available_qty) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, isbn);
            pstmt.setInt(4, Integer.parseInt(qtyStr));
            pstmt.setInt(5, Integer.parseInt(qtyStr)); // available_qty-г quantity-тай тэнцүү болгох

            // Өгөгдлийн сан руу бичлэг нэмэх функц
            pstmt.executeUpdate();

            // Талбаруудыг цэвэрлэх
            txtBookName.clear();
            txtAuthor.clear();
            txtIsbn.clear();
            txtQuantity.clear();

            // ObservableList шинэчлэх функц
            loadBooksFromDatabase();

            showAlert("Амжилттай", "Шинэ ном амжилттай бүртгэлээ.", Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            showAlert("Алдаа", "Тоо ширхэг талбарт заавал тоо оруулна уу!", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Алдаа", "Өгөгдлийн санд хадгалахад алдаа гарлаа.", Alert.AlertType.ERROR);
        }
    }

    private void loadBooksFromDatabase() {
        bookList.clear();
        String query = "SELECT * FROM book";

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                bookList.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("quantity"),
                        rs.getInt("available_qty")));
            }

            if (tableBook != null) {
                tableBook.setItems(bookList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
