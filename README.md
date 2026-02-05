#  SAWITKU — Sistem Manajemen Kebun Kelapa Sawit

**SAWITKU** adalah aplikasi berbasis web untuk membantu pengelolaan operasional kebun kelapa sawit, khususnya dalam pendataan karyawan, absensi panen, serta rekap hasil panen,
Sistem ini dibangun menggunakan arsitektur MVC (Model-View-Controller) agar terstruktur, mudah dikembangkan, dan mudah dipelihara.

---

##  Tujuan Aplikasi

Aplikasi ini dibuat untuk:
- Mempermudah administrasi data tenaga kerja kebun  
- Mencatat absensi dan hasil panen secara terstruktur    
- Menghasilkan laporan yang siap dicetak atau diekspor ke PDF  

---

##  Fitur Utama

###  Manajemen Karyawan
- Tambah, ubah, hapus, dan lihat data karyawan
- Penyimpanan data terpusat dalam database

### Absensi Panen
- Input kehadiran karyawan saat panen
- Pencatatan tanggal dan lokasi kerja

###  Rekap Hasil Panen
- Input jumlah panen per karyawan
- Data terhubung langsung dengan perhitungan upah

###  Laporan & Export
- Laporan data karyawan
- Laporan absensi
- Laporan hasil panen
- Export laporan ke **PDF**

###  Dashboard Interaktif
- Tampilan ringkasan data
- Antarmuka bernuansa hijau sesuai tema perkebunan
- Desain modern menggunakan Tailwind CSS

---

##  Arsitektur Sistem

Aplikasi ini menggunakan pola **MVC (Model-View-Controller)**:

- **Model** → Mengelola struktur data & koneksi database  
- **View** → Tampilan antarmuka pengguna (UI)  
- **Controller** → Penghubung antara Model dan View, mengatur alur logika aplikasi  

---

##  Depedency yang Digunakan

| Dependency        | Keterangan |
|------------------|------------|
| **Java**         | Bahasa pemrograman utama |
| **Spring Boot**  | Framework backend berbasis MVC |
| **Thymeleaf**    | Template engine untuk tampilan |
| **MySQL**        | Database penyimpanan data |
| **Tailwind CSS** | Styling antarmuka |
| **iText / PDF Library** | Pembuatan laporan PDF |

---

##  Cara Menjalankan Project

### 1️ Clone Repository
```bash
git clone https://github.com/Violennx/Sistem-Absensi-dan-Panen-Pelaporan-Karyawan-Sawit.git
cd Sistem-Absensi-dan-Panen-Pelaporan-Karyawan-Sawit
