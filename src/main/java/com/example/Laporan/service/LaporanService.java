package com.example.Laporan.service;

import com.example.Laporan.model.Absensi;
import com.example.Laporan.repository.AbsensiRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaporanService {

    @Autowired
    private AbsensiRepository absensiRepository;

    public List<Absensi> getLaporan( 
            Integer karyawanId,
            String nama,
            Integer bulan,
            Integer tahun) { 

        return absensiRepository.findLaporan(
                karyawanId, nama, bulan, tahun);
    }

    public long getTotalKaryawan() {
        return absensiRepository.countKaryawan();
    }
}



