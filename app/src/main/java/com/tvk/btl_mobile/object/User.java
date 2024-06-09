package com.tvk.btl_mobile.object;

public class User {
    private int id;
    private String tentk, mk, email;

    User ()  {
    }

    public User(String tentk, String mk, String email) {
        this.tentk = tentk;
        this.mk = mk;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
