package com.example.Laporan.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


@Repository
public class DashboardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public int getTotalKaryawan() {
        String sql = "SELECT COUNT(*) FROM karyawan";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    
    public int getKaryawanAktif() {
        String sql = "SELECT COUNT(DISTINCT karyawan_id) FROM absensi " +
                     "WHERE status = 'HADIR' AND DATE(tanggal) = CURDATE()";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    
    public int getPanenHariIni() {
        String sql = "SELECT SUM(jumlah_panen) FROM absensi " +
                     "WHERE status = 'HADIR' AND DATE(tanggal) = CURDATE()";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    
    public Map<String, Integer> getAbsensiHariIni() {
        String sql = "SELECT status, COUNT(*) AS jumlah " +
                     "FROM absensi WHERE DATE(tanggal) = CURDATE() GROUP BY status";
        return jdbcTemplate.query(sql, rs -> {
            Map<String, Integer> map = new HashMap<>();
            while (rs.next()) {
                map.put(rs.getString("status"), rs.getInt("jumlah"));
            }
            return map;
        });
    }

    
    public Map<String, Integer> getPerformaPanen() {
        String sql = "SELECT k.nama, SUM(a.jumlah_panen) AS total_panen " +
                     "FROM absensi a JOIN karyawan k ON a.karyawan_id = k.id " +
                     "WHERE a.status = 'HADIR' GROUP BY k.nama";
        return jdbcTemplate.query(sql, rs -> {
            Map<String, Integer> map = new HashMap<>();
            while (rs.next()) {
                map.put(rs.getString("nama"), rs.getInt("total_panen"));
            }
            return map;
        });
    }

    
    public List<Map<String, Object>> getTopKaryawanPanen() {
        String sql = "SELECT k.nama, SUM(a.jumlah_panen) AS total_panen " +
                     "FROM absensi a JOIN karyawan k ON a.karyawan_id = k.id " +
                     "WHERE a.status = 'HADIR' GROUP BY k.nama " +
                     "ORDER BY total_panen DESC LIMIT 3";
        return jdbcTemplate.queryForList(sql);
    }

    
    public Map<String, Integer> getKaryawanPerJabatan() {
        String sql = "SELECT jabatan, COUNT(*) AS jumlah FROM karyawan GROUP BY jabatan";
        return jdbcTemplate.query(sql, rs -> {
            Map<String, Integer> map = new HashMap<>();
            while (rs.next()) {
                map.put(rs.getString("jabatan"), rs.getInt("jumlah"));
            }
            return map;
        });
    }
}
