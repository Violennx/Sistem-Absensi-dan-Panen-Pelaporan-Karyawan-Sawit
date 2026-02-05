package com.example.Laporan.controller;

import com.example.Laporan.model.Absensi;

import com.example.Laporan.service.AbsensiService;
import com.example.Laporan.service.KaryawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/absensi")
public class AbsensiController {

@Autowired
    private AbsensiService absensiService;

    @Autowired
    private KaryawanService karyawanService;

    
    @GetMapping
    public String page(Model model) {
        model.addAttribute("page", "Absensi");
        model.addAttribute("listAbsensi", absensiService.getAll());
        model.addAttribute("listKaryawan", karyawanService.getAll());
        model.addAttribute("absensi", new Absensi());
        return "absensi"; 
    }

    
    @PostMapping("/save")
    public String save(@ModelAttribute Absensi absensi) {
        if (absensi.getId() == null) {
            absensiService.save(absensi); 
        } else {
            absensiService.update(absensi);
        }  
        return "redirect:/absensi";  

    

    }

    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute("absensi", absensiService.findById(id));
    return "absensi-edit";
    }

    
    @PostMapping("/update")
    public String update(@ModelAttribute Absensi a) {
    absensiService.update(a); 
    return "redirect:/absensi";

    }
    
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        absensiService.deletebyId(id);
        return "redirect:/absensi";
    }

}



