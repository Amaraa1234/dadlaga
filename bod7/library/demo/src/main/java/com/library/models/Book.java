package com.library.models;

public class Book {
    private int bookId; // Саны book_id талбарт таарна
    private String name; // Саны name талбарт таарна
    private String too; // Саны too талбарт таарна

    public Book(int bookId, String name, String too) {
        this.bookId = bookId;
        this.name = name;
        this.too = too;
    }

    // Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToo() {
        return too;
    }

    public void setToo(String too) {
        this.too = too;
    }
}
