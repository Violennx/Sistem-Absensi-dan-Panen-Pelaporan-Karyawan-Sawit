package com.example.Laporan.model;

import java.time.LocalDate;

public class Absensi {

    private Integer id;
    private Integer karyawanId;
    private String nama;
    private LocalDate tanggal;
    private String status;
    private String blok;
    private Integer jumlahPanen;   

    public Absensi() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKaryawanId() {
        return karyawanId;
    }

    public void setKaryawanId(Integer karyawanId) {
        this.karyawanId = karyawanId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBlok() {
        return blok;
    }

    public void setBlok(String blok) {
        this.blok = blok;
    }

    public Integer getJumlahPanen() {
        return jumlahPanen;
    }

    public void setJumlahPanen(Integer jumlahPanen) {
        this.jumlahPanen = jumlahPanen;
    }
}
