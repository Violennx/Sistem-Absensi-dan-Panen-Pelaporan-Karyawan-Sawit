package com.example.Laporan.controller;

import com.example.Laporan.model.Absensi;
import com.example.Laporan.service.KaryawanService;
import com.example.Laporan.service.LaporanService;
import com.util.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/laporan")
public class LaporanController {

    @Autowired
    private LaporanService laporanService;

    @Autowired
    private KaryawanService karyawanService;

    @GetMapping
    public String laporan(
            @RequestParam(required = false) Integer karyawanId,
            @RequestParam(required = false) String nama,
            @RequestParam(required = false) Integer bulan,
            @RequestParam(required = false) Integer tahun,
            Model model) {

        if (bulan == null) bulan = LocalDate.now().getMonthValue();
        if (tahun == null) tahun = LocalDate.now().getYear();

        model.addAttribute("listKaryawan", karyawanService.getAll());
        model.addAttribute("listLaporan",
                laporanService.getLaporan(karyawanId, nama, bulan, tahun));
        model.addAttribute("bulan", bulan);
        model.addAttribute("tahun", tahun);

        return "laporan";
    }

    
    @GetMapping("/pdf")
        public void cetakPdf(
                @RequestParam(required = false) Integer karyawanId,
                @RequestParam(required = false) String nama,
                @RequestParam Integer bulan,
                @RequestParam Integer tahun,
                HttpServletResponse response) throws Exception {
        List<Absensi> list =
                laporanService.getLaporan(karyawanId, nama, bulan, tahun);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=laporan.pdf");

        PdfGenerator.generateLaporan(
                response.getOutputStream(),
                list,
                "Laporan Kerja Bulan " + bulan + " Tahun " + tahun
        );
}


}


