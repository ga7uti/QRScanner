package com.abc.qrscanner;

public class QrDetails {

    private String name;
    private String number;
    private String email;
    private String gender;

    public QrDetails(){}

    public QrDetails(String name,  String number, String email, String gender) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
