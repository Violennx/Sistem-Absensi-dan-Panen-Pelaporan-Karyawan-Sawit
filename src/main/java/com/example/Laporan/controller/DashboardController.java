package com.example.Laporan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/dashboard")
public String dashboard(Model model) {

    Integer totalKaryawan = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM karyawan", Integer.class);

    Integer karyawanAktif = totalKaryawan; 

    Integer panenHariIni = jdbcTemplate.queryForObject(
            "SELECT COALESCE(SUM(jumlah_panen),0) FROM absensi WHERE tanggal = CURDATE()", Integer.class);

    Integer hadir = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM absensi WHERE tanggal = CURDATE() AND status='Hadir'", Integer.class);

    Integer izin = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM absensi WHERE tanggal = CURDATE() AND status='Izin'", Integer.class);

    Integer sakit = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM absensi WHERE tanggal = CURDATE() AND status='Sakit'", Integer.class);

    Integer alpa = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM karyawan WHERE id NOT IN " +
                    "(SELECT karyawan_id FROM absensi WHERE tanggal = CURDATE())", Integer.class);

    
    List<String> labels = jdbcTemplate.queryForList(
            "SELECT nama FROM karyawan", String.class);

    List<Integer> dataValues = new ArrayList<>();
    for (String nama : labels) {
        Integer panen = jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(a.jumlah_panen),0) " +
                        "FROM absensi a JOIN karyawan k ON a.karyawan_id = k.id " +
                        "WHERE k.nama = ? AND a.tanggal = CURDATE()",
                Integer.class, nama);
        dataValues.add(panen);
    }

    // ==== BAR CHART JABATAN ====
    List<String> jabatanLabels = jdbcTemplate.queryForList(
            "SELECT DISTINCT jabatan FROM karyawan", String.class);

    List<Integer> jabatanValues = new ArrayList<>();
    for (String jabatan : jabatanLabels) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM karyawan WHERE jabatan = ?",
                Integer.class, jabatan);
        jabatanValues.add(count);
    }

    // ==== KIRIM KE VIEW ====
    model.addAttribute("totalKaryawan", totalKaryawan);
    model.addAttribute("karyawanAktif", karyawanAktif);
    model.addAttribute("panenHariIni", panenHariIni);
    model.addAttribute("hadir", hadir);
    model.addAttribute("izin", izin);
    model.addAttribute("sakit", sakit);
    model.addAttribute("alpa", alpa);

    model.addAttribute("labels", labels);
    model.addAttribute("dataValues", dataValues);
    model.addAttribute("jabatanLabels", jabatanLabels);
    model.addAttribute("jabatanValues", jabatanValues);

    return "dashboard";
}

}
