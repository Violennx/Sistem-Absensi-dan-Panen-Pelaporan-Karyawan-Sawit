package com.example.Laporan.repository;

import com.example.Laporan.model.Absensi;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import com.example.Laporan.model.WeeklyTotal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Repository
public class AbsensiRepository {

    @Autowired
    private DataSource dataSource;

    
    public void save(Absensi a) {
        String sql = "INSERT INTO absensi (karyawan_id, tanggal, status, blok, jumlah_panen) VALUES (?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getKaryawanId());

            if (a.getTanggal() != null) {
                ps.setDate(2, Date.valueOf(a.getTanggal()));
            } else {
                ps.setDate(2, null);
            }

            ps.setString(3, a.getStatus());
            ps.setString(4, a.getBlok());
            ps.setInt(5, a.getJumlahPanen());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public List<Absensi> findAll() {
        List<Absensi> list = new ArrayList<>();
        String sql = "SELECT a.*, k.nama FROM absensi a JOIN karyawan k ON a.karyawan_id = k.id ORDER BY a.tanggal DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Absensi a = new Absensi();
                a.setId(rs.getInt("id"));
                a.setKaryawanId(rs.getInt("karyawan_id"));
                a.setNama(rs.getString("nama"));

                Date sqlDate = rs.getDate("tanggal");
                if (sqlDate != null) {
                    a.setTanggal(sqlDate.toLocalDate());
                }

                a.setStatus(rs.getString("status"));
                a.setBlok(rs.getString("blok"));
                a.setJumlahPanen(rs.getInt("jumlah_panen"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public void update(Absensi a) {
        String sql = "UPDATE absensi SET karyawan_id=?, tanggal=?, status=?, blok=?, jumlah_panen=? WHERE id=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getKaryawanId());

            if (a.getTanggal() != null) {
                ps.setDate(2, Date.valueOf(a.getTanggal()));
            } else {
                ps.setDate(2, null);
            }

            ps.setString(3, a.getStatus());
            ps.setString(4, a.getBlok());
            ps.setInt(5, a.getJumlahPanen());
            ps.setInt(6, a.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void deleteById(Integer id) {
        String sql = "DELETE FROM absensi WHERE id=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public Absensi findById(Integer id) {
        String sql = "SELECT a.*, k.nama FROM absensi a JOIN karyawan k ON a.karyawan_id = k.id WHERE a.id=?";
        Absensi a = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new Absensi();
                a.setId(rs.getInt("id"));
                a.setKaryawanId(rs.getInt("karyawan_id"));
                a.setNama(rs.getString("nama"));

                Date sqlDate = rs.getDate("tanggal");
                if (sqlDate != null) {
                    a.setTanggal(sqlDate.toLocalDate());
                }

                a.setStatus(rs.getString("status"));
                a.setBlok(rs.getString("blok"));
                a.setJumlahPanen(rs.getInt("jumlah_panen"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
    

    
    public List<WeeklyTotal> findWeeklyTotals(LocalDate start, LocalDate end) {
        List<WeeklyTotal> list = new ArrayList<>();
        String sql = "SELECT k.nama, COALESCE(SUM(a.jumlah_panen),0) AS total " +
                     "FROM absensi a JOIN karyawan k ON a.karyawan_id = k.id " +
                     "WHERE a.tanggal BETWEEN ? AND ? " +
                     "GROUP BY k.nama";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WeeklyTotal wt = new WeeklyTotal();
                wt.setNama(rs.getString("nama"));
                wt.setTotal(rs.getInt("total"));
                list.add(wt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Absensi> findLaporan(Integer karyawanId, String nama, Integer bulan, Integer tahun) {

    List<Absensi> list = new ArrayList<>();
        
    String sql;
    boolean pakaiKaryawan = (karyawanId != null);

    if (pakaiKaryawan) {
        sql = """
            SELECT a.id, a.karyawan_id, k.nama, a.tanggal, a.status, a.blok, a.jumlah_panen
            FROM absensi a
            JOIN karyawan k ON a.karyawan_id = k.id
            WHERE a.karyawan_id = ?
              AND MONTH(a.tanggal) = ?
              AND YEAR(a.tanggal) = ?
            ORDER BY a.tanggal
        """;
    } else {
        sql = """
            SELECT a.id, a.karyawan_id, k.nama, a.tanggal, a.status, a.blok, a.jumlah_panen
            FROM absensi a
            JOIN karyawan k ON a.karyawan_id = k.id
            WHERE MONTH(a.tanggal) = ?
              AND YEAR(a.tanggal) = ?
            ORDER BY a.tanggal
        """;
    }

    try (Connection conn = dataSource.getConnection();//membuka koneksi ke database
         PreparedStatement ps = conn.prepareStatement(sql)) {

        int idx = 1;

        if (pakaiKaryawan) {
            ps.setInt(idx++, karyawanId);
        }

        ps.setInt(idx++, bulan);
        ps.setInt(idx, tahun);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Absensi a = new Absensi();//membuat objek absensi baru untuk setiap baris hasil
            a.setId(rs.getInt("id"));//mengatur properti objek absensi dengan nilai dari hasil query
            a.setKaryawanId(rs.getInt("karyawan_id"));
            a.setNama(rs.getString("nama"));
            a.setTanggal(rs.getTimestamp("tanggal").toLocalDateTime().toLocalDate());//mengonversi nilai tanggal dari SQL ke LocalDate
            a.setStatus(rs.getString("status"));
            a.setBlok(rs.getString("blok"));
            a.setJumlahPanen(rs.getInt("jumlah_panen"));
            list.add(a);
        }//

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

    public long countKaryawan() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countKaryawan'");
    }

}