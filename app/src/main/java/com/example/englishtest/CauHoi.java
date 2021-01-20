package com.example.englishtest;

public class CauHoi {
    private int id;
    private String hoi, daa, dab, dac, dad, datrue;

    public CauHoi(int id, String hoi, String daa, String dab, String dac, String dad, String datrue) {
        this.id = id;
        this.hoi = hoi;
        this.daa = daa;
        this.dab = dab;
        this.dac = dac;
        this.dad = dad;
        this.datrue = datrue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoi() {
        return hoi;
    }

    public void setHoi(String hoi) {
        this.hoi = hoi;
    }

    public String getDaa() {
        return daa;
    }

    public void setDaa(String daa) {
        this.daa = daa;
    }

    public String getDab() {
        return dab;
    }

    public void setDab(String dab) {
        this.dab = dab;
    }

    public String getDac() {
        return dac;
    }

    public void setDac(String dac) {
        this.dac = dac;
    }

    public String getDad() {
        return dad;
    }

    public void setDad(String dad) {
        this.dad = dad;
    }

    public String getDatrue() {
        return datrue;
    }

    public void setDatrue(String datrue) {
        this.datrue = datrue;
    }
}
