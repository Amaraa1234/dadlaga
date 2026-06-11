package com.library.models;

public class Member {
    private int memberId;
    private String surname;
    private String name;
    private String phone;
    private String email;

    // Конструктор
    public Member(int memberId, String surname, String name, String phone, String email) {
        this.memberId = memberId;
        this.surname = surname;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getter болон Setter-үүд (JavaFX TableView-д заавал хэрэгтэй)
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}