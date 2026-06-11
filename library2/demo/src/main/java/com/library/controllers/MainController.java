package com.library.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.library.models.Book;
import com.library.models.Member;
import com.library.database.DBConnection;
import com.library.models.Rental;

public class MainController {

    @FXML
    private Button btnAddBook;
    @FXML
    private TextField txtAuthor;
    @FXML
    private TextField txtBookName;
    @FXML
    private TextField txtBookSearch;
    @FXML
    private TextField txtIsbin;
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
    @FXML
    private TableColumn<Book, Integer> colAv;

    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtReaderSearch;
    @FXML
    private Button btnReaderOk;

    @FXML
    private TableView<Member> tableReader;
    @FXML
    private TableColumn<Member, Integer> colReaderId;
    @FXML
    private TableColumn<Member, String> colReaderLastName;
    @FXML
    private TableColumn<Member, String> colReaderFirstName;
    @FXML
    private TableColumn<Member, String> colReaderPhone;
    @FXML
    private TableColumn<Member, String> colReaderEmail;

    private ObservableList<Member> memberList = FXCollections.observableArrayList();

    @FXML
    private TableView<Rental> tableRent;
    @FXML
    private TableColumn<Rental, Integer> colRentId;
    @FXML
    private TableColumn<Rental, String> colRentReaderName;
    @FXML
    private TableColumn<Rental, String> colRentBookName;
    @FXML
    private TableColumn<Rental, String> colRentDate;
    @FXML
    private TableColumn<Rental, String> colDueDate;
    @FXML
    private TableColumn<Rental, String> colReturnDate;
    @FXML
    private TableColumn<Rental, String> colRentStatus;

    @FXML
    private RadioButton radioAll;
    @FXML
    private RadioButton radioOverdue;
    @FXML
    private ToggleGroup rentToggleGroup;

    // Шинэ цонхонд ашиглагдах ComboBox элементүүд
    @FXML
    private ComboBox<Member> cmbMember;
    @FXML
    private ComboBox<Book> cmbBook;
    @FXML
    private Button btnSubmitRent;

    private ObservableList<Rental> rentalList = FXCollections.observableArrayList();
    private FilteredList<Rental> filteredRentals;
    private static Stage dialogStage; // Жижиг цонхыг хаахад ашиглана

    @FXML
    public void initialize() {
        if (tableBook != null) {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
            colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            colAv.setCellValueFactory(new PropertyValueFactory<>("availableQty"));
        }

        if (tableReader != null) {
            colReaderId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
            colReaderLastName.setCellValueFactory(new PropertyValueFactory<>("surname"));
            colReaderFirstName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colReaderPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            colReaderEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        }

        if (tableRent != null) {
            colRentId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colRentReaderName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
            colRentBookName.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
            colRentDate.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
            colDueDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
            colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnedOn"));
            colRentStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            filteredRentals = new FilteredList<>(rentalList, r -> true);
            tableRent.setItems(filteredRentals);
        }

        if (radioAll != null && radioOverdue != null) {
            if (rentToggleGroup == null) {
                rentToggleGroup = new ToggleGroup();
                radioAll.setToggleGroup(rentToggleGroup);
                radioOverdue.setToggleGroup(rentToggleGroup);
            }
            radioAll.setSelected(true);
            rentToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
                setupRentalFilter();
            });
        }

        // Хэрэв ComboBox-ууд одоогийн FXML-д олдвол тэдгээрийн форматыг тохируулна
        if (cmbMember != null && cmbBook != null) {
            setupComboBoxConverters();
            cmbMember.setItems(memberList);
            cmbBook.setItems(bookList);
        }

        setupBookSearch();
        setupReaderSearch();
        loadInitialData();
    }

    private void setupComboBoxConverters() {
        cmbMember.setConverter(new StringConverter<Member>() {
            @Override
            public String toString(Member m) {
                return m == null ? "" : m.getSurname() + " " + m.getName();
            }

            @Override
            public Member fromString(String s) {
                return null;
            }
        });
        cmbBook.setConverter(new StringConverter<Book>() {
            @Override
            public String toString(Book b) {
                return b == null ? "" : b.getTitle() + " (Үлдэгдэл: " + b.getAvailableQty() + ")";
            }

            @Override
            public Book fromString(String s) {
                return null;
            }
        });
    }

    private void loadInitialData() {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                loadBooksFromDatabase();
                loadMembersFromDatabase();
                loadRentalsFromDatabase();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Түрээслэх товч дарахад шинэ жижиг цонх нээх функц
    @FXML
    void onRentBookClick(ActionEvent event) {
        try {
            loadInitialData(); // Хамгийн сүүлийн үеийн датаг ачаалах
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/turees.fxml"));
            Parent root = loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle("Шинэ түрээс бүртгэх");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));
            dialogStage.setResizable(false);
            dialogStage.showAndWait();

            loadInitialData(); // Цонх хаагдсаны дараа үндсэн хүснэгтийг шинэчлэх
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Алдаа", "Түрээсийн цонхыг нээхэд алдаа гарлаа!", AlertType.ERROR);
        }
    }

    // Жижиг цонхны "ТҮРЭЭСЛҮҮЛЭХ" товч дээр ажиллах логик
    @FXML
    void onConfirmRentClick(ActionEvent event) {
        Member selectedMember = cmbMember.getSelectionModel().getSelectedItem();
        Book selectedBook = cmbBook.getSelectionModel().getSelectedItem();

        if (selectedMember == null || selectedBook == null) {
            showAlert("Анхааруулга", "Уншигч болон номыг заавал сонгоно уу!", AlertType.WARNING);
            return;
        }

        if (selectedBook.getAvailableQty() <= 0) {
            showAlert("Алдаа", "Уг номын үлдэгдэл хүрэлцээгүй байна!", AlertType.ERROR);
            return;
        }

        LocalDate rentDate = LocalDate.now();
        LocalDate dueDate = rentDate.plusDays(14);

        String insertRentQuery = "INSERT INTO library_db.borrow_records (memberid, bookid, borrow_date, due_date, status) VALUES (?, ?, ?, ?, 'Түрээсэлсэн')";
        String updateBookQuery = "UPDATE book SET available_qty = available_qty - 1 WHERE book_id = ?";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtRent = conn.prepareStatement(insertRentQuery)) {
                pstmtRent.setInt(1, selectedMember.getMemberId());
                pstmtRent.setInt(2, selectedBook.getId());
                pstmtRent.setDate(3, java.sql.Date.valueOf(rentDate));
                pstmtRent.setDate(4, java.sql.Date.valueOf(dueDate));
                pstmtRent.executeUpdate();
            }

            try (PreparedStatement pstmtBook = conn.prepareStatement(updateBookQuery)) {
                pstmtBook.setInt(1, selectedBook.getId());
                pstmtBook.executeUpdate();
            }

            conn.commit();
            showAlert("Амжилттай", "Ном амжилттай түрээслүүллээ.", AlertType.INFORMATION);

            if (dialogStage != null) {
                dialogStage.close(); // Амжилттай болбол цонхыг хаана
            }

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            showAlert("Алдаа", "Түрээсийг хадгалахад алдаа гарлаа!", AlertType.ERROR);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void onReturnBookClick(ActionEvent event) {
        Rental selectedRental = tableRent.getSelectionModel().getSelectedItem();

        if (selectedRental == null) {
            showAlert("Анхааруулга", "Буцаах номын түрээсийн бичлэгийг сонгоно уу!", AlertType.WARNING);
            return;
        }

        if ("Буцаасан".equals(selectedRental.getStatus())) {
            showAlert("Анхааруулга", "Энэ ном аль хэдийн буцаагдсан байна!", AlertType.WARNING);
            return;
        }

        LocalDate returnDate = LocalDate.now();
        String updateRentQuery = "UPDATE library_db.borrow_records SET return_date = ?, status = 'Буцаасан' WHERE record_id = ?";
        String updateBookQuery = "UPDATE book SET available_qty = available_qty + 1 WHERE book_id = (SELECT bookid FROM library_db.borrow_records WHERE record_id = ?)";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtRent = conn.prepareStatement(updateRentQuery)) {
                pstmtRent.setDate(1, java.sql.Date.valueOf(returnDate));
                pstmtRent.setInt(2, selectedRental.getId());
                pstmtRent.executeUpdate();
            }

            try (PreparedStatement pstmtBook = conn.prepareStatement(updateBookQuery)) {
                pstmtBook.setInt(1, selectedRental.getId());
                pstmtBook.executeUpdate();
            }

            conn.commit();
            loadInitialData();
            showAlert("Amjilttai", "Ном амжилттай буцаагдлаа.", AlertType.INFORMATION);

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            showAlert("Алдаа", "Ном буцаах үйлдэлд алдаа гарлаа!", AlertType.ERROR);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadRentalsFromDatabase() {
        if (tableRent == null)
            return;
        rentalList.clear();
        String query = "SELECT br.record_id, CONCAT(m.surname, ' ', m.name) AS member_name, b.title AS book_title, " +
                "br.borrow_date, br.due_date, br.return_date, br.status " +
                "FROM library_db.borrow_records br " +
                "JOIN library_db.member m ON br.memberid = m.member_id " +
                "JOIN library_db.book b ON br.bookid = b.book_id";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                rentalList
                        .add(new Rental(rs.getInt("record_id"), rs.getString("member_name"), rs.getString("book_title"),
                                rs.getString("borrow_date"), rs.getString("due_date"), rs.getString("return_date"),
                                rs.getString("status")));
            }
            setupRentalFilter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadBooksFromDatabase() {
        bookList.clear();
        String query = "SELECT * FROM book";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                bookList.add(new Book(rs.getInt("book_id"), rs.getString("title"), rs.getString("author"),
                        rs.getString("isbn"), rs.getInt("quantity"), rs.getInt("available_qty")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadMembersFromDatabase() {
        memberList.clear();
        String query = "SELECT * FROM library_db.member";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                memberList.add(new Member(rs.getInt("member_id"), rs.getString("surname"), rs.getString("name"),
                        rs.getString("phone"), rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupRentalFilter() {
        if (filteredRentals == null)
            return;
        filteredRentals.setPredicate(rental -> {
            if (radioAll.isSelected())
                return true;
            if (radioOverdue.isSelected()) {
                if ("Буцаасан".equals(rental.getStatus()))
                    return false;
                if (rental.getReturnDate() != null) {
                    try {
                        return LocalDate.now().isAfter(LocalDate.parse(rental.getReturnDate()));
                    } catch (Exception e) {
                        return false;
                    }
                }
            }
            return true;
        });
    }

    private void setupBookSearch() {
        if (txtBookSearch != null && tableBook != null) {
            FilteredList<Book> filteredBooks = new FilteredList<>(bookList, b -> true);
            txtBookSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredBooks.setPredicate(book -> {
                    if (newValue == null || newValue.isEmpty())
                        return true;
                    String filter = newValue.toLowerCase();
                    return (book.getTitle() != null && book.getTitle().toLowerCase().contains(filter)) ||
                            (book.getAuthor() != null && book.getAuthor().toLowerCase().contains(filter)) ||
                            (book.getIsbn() != null && book.getIsbn().toLowerCase().contains(filter));
                });
            });
            SortedList<Book> sortedBooks = new SortedList<>(filteredBooks);
            sortedBooks.comparatorProperty().bind(tableBook.comparatorProperty());
            tableBook.setItems(sortedBooks);
        }
    }

    private void setupReaderSearch() {
        if (txtReaderSearch != null && tableReader != null) {
            FilteredList<Member> filteredMembers = new FilteredList<>(memberList, m -> true);
            txtReaderSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredMembers.setPredicate(member -> {
                    if (newValue == null || newValue.isEmpty())
                        return true;
                    String filter = newValue.toLowerCase();
                    return (member.getSurname() != null && member.getSurname().toLowerCase().contains(filter)) ||
                            (member.getName() != null && member.getName().toLowerCase().contains(filter)) ||
                            (member.getPhone() != null && member.getPhone().toLowerCase().contains(filter));
                });
            });
            SortedList<Member> sortedMembers = new SortedList<>(filteredMembers);
            sortedMembers.comparatorProperty().bind(tableReader.comparatorProperty());
            tableReader.setItems(sortedMembers);
        }
    }

    @FXML
    void onAddBookClick(ActionEvent event) {
        String title = txtBookName.getText();
        String author = txtAuthor.getText();
        String isbn = txtIsbin.getText();
        String qtyStr = txtQuantity.getText();
        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || qtyStr.isEmpty()) {
            showAlert("Алдаа", "Бүх талбарыг бөглөнө үү!", AlertType.ERROR);
            return;
        }
        String query = "INSERT INTO book (title, author, isbn, quantity, available_qty) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, isbn);
            pstmt.setInt(4, Integer.parseInt(qtyStr));
            pstmt.setInt(5, Integer.parseInt(qtyStr));
            pstmt.executeUpdate();
            txtBookName.clear();
            txtAuthor.clear();
            txtIsbin.clear();
            txtQuantity.clear();
            loadBooksFromDatabase();
            showAlert("Амжилттай", "Шинэ ном амжилттай бүртгэлээ.", AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Алдаа", "Хадгалахад алдаа гарлаа.", AlertType.ERROR);
        }
    }

    @FXML
    void onAddReaderClick(ActionEvent event) {
        String surname = txtLastName.getText();
        String name = txtFirstName.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        if (surname.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            showAlert("Алдаа", "Овог, Нэр, Утаа заавал бөглөнө үү!", AlertType.ERROR);
            return;
        }
        String query = "INSERT INTO library_db.member (surname, name, phone, email, reg_date) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, surname);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            pstmt.setString(4, email.isEmpty() ? null : email);
            pstmt.executeUpdate();
            txtLastName.clear();
            txtFirstName.clear();
            txtPhone.clear();
            txtEmail.clear();
            loadMembersFromDatabase();
            showAlert("Амжилттай", "Уншигч амжилттай бүртгэгдлээ.", AlertType.INFORMATION);
        } catch (SQLException e) {
            showAlert("Алдаа", "Хадгалахад алдаа гарлаа.", AlertType.ERROR);
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