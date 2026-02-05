package com.example.Laporan.repository;


import com.example.Laporan.model.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class KaryawanRepository {
@Autowired
    private DataSource dataSource;

    public List<Karyawan> findAll() {
        List<Karyawan> list = new ArrayList<>();
        String sql = "SELECT * FROM karyawan";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Karyawan k = new Karyawan();
                k.setId(rs.getInt("id"));
                k.setKodeKaryawan(rs.getString("kode_karyawan"));
                k.setNama(rs.getString("nama"));
                k.setTanggalLahir(rs.getDate("tanggal_lahir").toLocalDate());
                k.setJabatan(rs.getString("jabatan"));
                list.add(k);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public Karyawan findByKode(String kode) {
    String sql = "SELECT * FROM karyawan WHERE kode_karyawan = ?";
    try (Connection conn = dataSource.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Karyawan k = new Karyawan();
            k.setId(rs.getInt("id"));
            k.setKodeKaryawan(rs.getString("kode_karyawan"));
            k.setNama(rs.getString("nama"));
            k.setTanggalLahir(rs.getDate("tanggal_lahir").toLocalDate());
            k.setJabatan(rs.getString("jabatan"));
            return k;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
    }




    
    public void save(Karyawan k) {
        String sql = "INSERT INTO karyawan(kode_karyawan,nama,tanggal_lahir,jabatan) VALUES (?,?,?,?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, k.getKodeKaryawan());
            ps.setString(2, k.getNama());
            ps.setDate(3, Date.valueOf(k.getTanggalLahir()));
            ps.setString(4, k.getJabatan());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByKode(String kodeKaryawan) {
    String sql = "DELETE FROM karyawan WHERE kode_karyawan = ?";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kodeKaryawan);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Karyawan findById(Integer id) {
    String sql = "SELECT * FROM karyawan WHERE id = ?";
    Karyawan k = null;

        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                k = new Karyawan();
                k.setId(rs.getInt("id"));
                k.setKodeKaryawan(rs.getString("kode_karyawan"));
                k.setNama(rs.getString("nama"));
                k.setTanggalLahir(rs.getDate("tanggal_lahir").toLocalDate());
                k.setJabatan(rs.getString("jabatan"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return k;
    }

    public void deleteById(Integer id) {
    String sql = "DELETE FROM karyawan WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    public void insert(Karyawan k) {
    String sql = """
        INSERT INTO karyawan(kode_karyawan, nama, tanggal_lahir, jabatan)
        VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, k.getKodeKaryawan());
            ps.setString(2, k.getNama());
            ps.setDate(3, Date.valueOf(k.getTanggalLahir()));
            ps.setString(4, k.getJabatan());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

}

    
    public void update(Karyawan k) {
    String sql = """
        UPDATE karyawan
        SET nama = ?, tanggal_lahir = ?, jabatan = ?
        WHERE id = ?
        """;

    try (Connection conn = dataSource.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, k.getNama());
        ps.setDate(2, Date.valueOf(k.getTanggalLahir()));
        ps.setString(3, k.getJabatan());
        ps.setInt(4, k.getId());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}



    

}