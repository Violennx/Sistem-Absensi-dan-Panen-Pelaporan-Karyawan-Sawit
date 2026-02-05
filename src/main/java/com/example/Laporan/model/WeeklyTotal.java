package com.example.Laporan.model;

public class WeeklyTotal {
    private String nama;
    private int total;

    public WeeklyTotal() {}

    public WeeklyTotal(String nama, int total) {
        this.nama = nama;
        this.total = total;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
