package com.abc.qrscanner;

public class QrDetails {

    private String name;
    private String qrcode;
    private String email;

    public QrDetails() {}

    public QrDetails(String name, String qrcode, String email) {
        this.name = name;
        this.qrcode = qrcode;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


