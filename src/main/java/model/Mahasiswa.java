/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class Mahasiswa {
    private String nim;
    private String nama;
    private String idBukuDipinjam;
    
//    constructor
    public Mahasiswa(String nim, String nama, String idBukuDipinjam) {
        this.nim = nim;
        this.nama = nama;
        this.idBukuDipinjam = idBukuDipinjam;
    }

//    getter
    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getIdBukuDipinjam() {
        return idBukuDipinjam;
    }

//    setter
    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setIdBukuDipinjam(String idBukuDipinjam) {
        this.idBukuDipinjam = idBukuDipinjam;
    }
}
