package com.library.controllers;

import com.library.database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainController {

    @FXML
    private Button btnAddBook;
    @FXML
    private TableView<BookData> tableBook;
    @FXML
    private TableColumn<BookData, Integer> colId;
    @FXML
    private TableColumn<BookData, String> colTitle;
    @FXML
    private TableColumn<BookData, String> colQuantity;

    @FXML
    private TextField txtBookName;
    @FXML
    private TextField txtQuantity;

    private ObservableList<BookData> bookList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("too"));

        btnAddBook.setOnAction(event -> handleAddBook());
        loadBooksFromDatabase();
    }

    private void loadBooksFromDatabase() {
        bookList.clear();
        String query = "SELECT * FROM book_id"; //
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                bookList.add(new BookData(
                        rs.getInt("book_id"), //
                        rs.getString("name"), //
                        rs.getString("too") //
                ));
            }
            tableBook.setItems(bookList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleAddBook() {
        String name = txtBookName.getText();
        String qty = txtQuantity.getText();
        if (name.isEmpty() || qty.isEmpty())
            return;

        String insertQuery = "INSERT INTO book_id (name, too) VALUES (?, ?)"; //
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setString(1, name);
            stmt.setString(2, qty);
            stmt.executeUpdate();
            txtBookName.clear();
            txtQuantity.clear();
            loadBooksFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class BookData {
        private int bookId;
        private String name;
        private String too;

        public BookData(int bookId, String name, String too) {
            this.bookId = bookId;
            this.name = name;
            this.too = too;
        }

        public int getBookId() {
            return bookId;
        }

        public String getName() {
            return name;
        }

        public String getToo() {
            return too;
        }
    }
}