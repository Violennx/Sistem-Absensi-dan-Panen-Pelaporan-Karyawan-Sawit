package com.example.Laporan.service;

import com.example.Laporan.model.Absensi;

import com.example.Laporan.repository.AbsensiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import com.example.Laporan.model.WeeklyTotal;

@Service
public class AbsensiService {
    
  @Autowired
    private AbsensiRepository repository;

    
    public void save(Absensi a) {
        repository.save(a);
    }

    
    public List<Absensi> getAll() {
        return repository.findAll();
    }

    
    public void update(Absensi a) {
        repository.update(a);
    }

    
    public Absensi findById(Integer id) {
        return repository.findById(id);
    }

    
    public void deletebyId(Integer id) {
        repository.deleteById(id);
    }

    
    public List<WeeklyTotal> getWeeklyTotals(LocalDate start, LocalDate end) {
        return repository.findWeeklyTotals(start, end);
    }

    

}
