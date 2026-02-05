package com.example.Laporan.service;

import com.example.Laporan.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository repo;

    public int getTotalKaryawan() { return repo.getTotalKaryawan(); }
    public int getKaryawanAktif() { return repo.getKaryawanAktif(); }
    public int getPanenHariIni() { return repo.getPanenHariIni(); }
    public Map<String, Integer> getAbsensiHariIni() { return repo.getAbsensiHariIni(); }
    public Map<String, Integer> getPerformaPanen() { return repo.getPerformaPanen(); }
    public List<Map<String, Object>> getTopKaryawanPanen() { return repo.getTopKaryawanPanen(); }
    public Map<String, Integer> getKaryawanPerJabatan() { return repo.getKaryawanPerJabatan(); }
}
