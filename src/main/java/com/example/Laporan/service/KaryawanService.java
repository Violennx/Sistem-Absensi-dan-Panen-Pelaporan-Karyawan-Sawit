package com.example.Laporan.service;

import com.example.Laporan.model.Karyawan;
import com.example.Laporan.repository.KaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaryawanService {

    @Autowired
    private KaryawanRepository repository;

    public List<Karyawan> getAll() {
        return repository.findAll();
    }
    
    
    public void save(Karyawan karyawan) {
        repository.save(karyawan);
    }

    
    
    public void update(Karyawan karyawan) {
        repository.update(karyawan);
    }

    public Karyawan findById(Integer id) {
        return repository.findById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
