package com.example.Laporan.controller;

import com.example.Laporan.model.Karyawan;
import com.example.Laporan.service.KaryawanService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KaryawanController {

    @Autowired
    private KaryawanService service;

    @GetMapping("/karyawan")
    public String page(Model model, HttpServletRequest request) {
        
        model.addAttribute("listKaryawan", service.getAll());
        
        model.addAttribute("karyawan", new Karyawan());

        return "index"; 
    }

    @PostMapping("/karyawan/save")
    public String save(@ModelAttribute Karyawan karyawan) {
        service.save(karyawan);
        return "redirect:/karyawan";
    }

    @GetMapping("/karyawan/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Karyawan existing = service.findById(id);
        model.addAttribute("karyawan", existing);
        model.addAttribute("listKaryawan", service.getAll());
        return "karyawan-edit";
    }

    @PostMapping("/karyawan/update")
    public String update(@ModelAttribute Karyawan karyawan) {
        service.update(karyawan);
        return "redirect:/karyawan";
    }

    @GetMapping("/karyawan/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/karyawan";
    }
}