/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
/**
 *
 * @author Acer
 */
public class Transaksi {
    private String idTransaksi;
    private String nim;
    private String namaMahasiswa;
    private String idBuku;
    private String judulBuku;
    private String kategori;
    private LocalDate tanggalPeminjaman;
    
//    onstructor
    public Transaksi(String idTransaksi, String nim, String namaMahasiswa, String idBuku, String judulBuku, String kategori, LocalDate tanggalPeminjaman) {
        this.idTransaksi = idTransaksi;
        this.nim = nim;
        this.namaMahasiswa = namaMahasiswa;
        this.idBuku = idBuku;
        this.judulBuku = judulBuku;
        this.kategori = kategori;
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

//    getter
    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getNim() {
        return nim;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public String getKategori() {
        return kategori;
    }

    public LocalDate getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

//    setter
    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setTanggalPeminjaman(LocalDate tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }
}
