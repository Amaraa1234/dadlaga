package com.library.models;

public class Rental {
    private int id;
    private String memberName;
    private String bookTitle;
    private String rentDate;
    private String returnDate; // Энэ нь Controller дээрх colDueDate буюу дуусах хугацаа
    private String returnedOn; // Энэ нь кодоор шинэчлэгдэх бодит буцаасан өдөр
    private String status; // Энэ нь кодоор шинэчлэгдэх статус

    // Конструктор
    public Rental(int id, String memberName, String bookTitle, String rentDate, String returnDate, String returnedOn,
            String status) {
        this.id = id;
        this.memberName = memberName;
        this.bookTitle = bookTitle;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.returnedOn = returnedOn;
        this.status = status;
    }

    // Getter-үүд
    public int getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getRentDate() {
        return rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getReturnedOn() {
        return returnedOn;
    }

    public String getStatus() {
        return status;
    }

    // Setter-үүд (Код дотор датаг шинэчлэхэд заавал хэрэгтэй)
    public void setReturnedOn(String returnedOn) {
        this.returnedOn = returnedOn;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}