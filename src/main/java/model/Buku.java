/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class Buku {
    private String idBuku;
    private String judul;
    private String kategori;
    private int stok;
    private int lantai;
    private int rak;
    private int posisi;
    
//    constructor
    public Buku(String idBuku, String judul, String kategori, int stok, int lantai, int rak, int posisi) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.kategori = kategori;
        this.stok = stok;
        this.lantai = lantai;
        this.rak = rak;
        this.posisi = posisi;
    }
    
//    getter
    public String getIdBuku() {
        return idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public String getKategori() {
        return kategori;
    }

    public int getStok() {
        return stok;
    }

    public int getLantai() {
        return lantai;
    }

    public int getRak() {
        return rak;
    }

    public int getPosisi() {
        return posisi;
    }
    
//    setter
    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setLantai(int lantai) {
        this.lantai = lantai;
    }

    public void setRak(int rak) {
        this.rak = rak;
    }

    public void setPosisi(int posisi) {
        this.posisi = posisi;
    }
    
 

}
