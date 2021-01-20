package com.example.englishtest;

public class Player {
    private int id;
    private String fullname, username, pass;
    private int diem1, diem2, diem3, diem4, diem5;

    public Player(int id, String fullname, String username, String pass, int diem1, int diem2, int diem3, int diem4, int diem5) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.pass = pass;
        this.diem1 = diem1;
        this.diem2 = diem2;
        this.diem3 = diem3;
        this.diem4 = diem4;
        this.diem5 = diem5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getDiem1() {
        return diem1;
    }

    public void setDiem1(int diem1) {
        this.diem1 = diem1;
    }

    public int getDiem2() {
        return diem2;
    }

    public void setDiem2(int diem2) {
        this.diem2 = diem2;
    }

    public int getDiem3() {
        return diem3;
    }

    public void setDiem3(int diem3) {
        this.diem3 = diem3;
    }

    public int getDiem4() {
        return diem4;
    }

    public void setDiem4(int diem4) {
        this.diem4 = diem4;
    }

    public int getDiem5() {
        return diem5;
    }

    public void setDiem5(int diem5) {
        this.diem5 = diem5;
    }
}
