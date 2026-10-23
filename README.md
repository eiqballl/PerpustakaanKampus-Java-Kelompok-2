# Sistem Manajemen Perpustakaan Kampus

Program Java sederhana untuk mengelola perpustakaan kampus seperti data koleksi buku, lokasi penyimpanan di rak, antrean peminjaman, sampai riwayat transaksi. Program ini dibuat sebagai tugas mata kuliah Pemrograman Berorientasi Objek dengan menerapkan enam konsep struktur data dalam satu program yang saling terintegrasi, plus menu interaktif berbasis console.

## Tugas Pemrograman Java
- ***Mata Kuliah:*** Pemrograman Berorientasi Objek
- ***Dosen Pengampu:*** M. Ibadurrahman Arrasyid Supriyanto M.Kom.
- ***Program Studi:*** Sistem Informasi
- ***Angkatan:*** 2025
- ***Kelompok:*** 2 Canopy

## Anggota Kelompok Canopy
1. **Muhammad Fahriel** - 2509116050 - B
2. **Iqbal Nurriz Ramadhan** - 2509116067 - B
3. **Alya Aulia** - 250911 - B
4. **Nabila Viviana** - 250911 - C
5. **Dapa Arkhabista** - 25091160 - A

## Fitur Program
- Lihat daftar kategori buku
- Kelola data buku (tambah, tampilkan, cari, hapus)
- Lihat lokasi penyimpanan buku (lantai, nomor rak, posisi)
- Kelola antrean peminjaman mahasiswa
- Proses peminjaman buku
- Lihat riwayat transaksi dan batalkan transaksi terakhir
- Lihat statistik peminjaman (mingguan, per kategori, total keseluruhan)

## Struktur Data yang Digunakan
| Konsep | Penerapan di Program |
|--------|----------------------|
| Array 1 Dimensi | Menyimpan daftar kategori buku |
| Array 2 Dimensi | Menyimpan jumlah peminjaman berdasarkan kategori dan hari dalam seminggu |
| Array 3 Dimensi | Merepresentasikan lokasi penyimpanan buku (lantai x nomor rak x posisi) |
| ArrayList | Mengelola daftar buku secara dinamis (tambah, tampil, hapus) |
| Stack (LIFO) | Menyimpan riwayat transaksi; transaksi terakhir bisa dilihat atau dibatalkan |
| Queue (FIFO) | Mengelola antrean mahasiswa yang ingin meminjam buku sesuai urutan kedatangan |

## Struktur File Proyek
```
src/main/java/
├── controller/
│   ├── BukuController.java         # Logika kelola buku + lokasi rak
│   ├── MahasiswaController.java    # Logika antrean mahasiswa
│   └── TransaksiController.java    # Logika peminjaman, riwayat, statistik
├── model/
│   ├── Buku.java                   # Entitas buku
│   ├── Mahasiswa.java              # Entitas mahasiswa
│   └── Transaksi.java              # Entitas transaksi peminjaman
├── view/
│   └── PerpustakaanView.java       # Menu interaktif menggunakan Scanner
└── main/perpustakaankampus_java_kelompok_2/
    └── PerpustakaanKampus_Java_Kelompok_2.java  # Main class
```
