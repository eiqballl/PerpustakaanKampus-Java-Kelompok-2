/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Buku;
/**
 *
 * @author Acer
 */
public class BukuController {
    // Array 1D: kategori tetap
    private final String[] kategoriBuku = {
        "Pemrograman", "Matematika", "Jaringan Komputer", "Basis Data"
    };
    // Array 3D: setiap slot berisi ID buku atau null jika kosong
    private final String[][][] lokasiBuku = new String[2][3][5];
    // ArrayList: daftar buku dinamis
    private final ArrayList<Buku> daftarBuku = new ArrayList<>();

    public String[] getKategoriBuku() { return kategoriBuku.clone(); }

    public String getKategori(int nomor) {
        if (nomor < 1 || nomor > kategoriBuku.length) return null;
        return kategoriBuku[nomor - 1];
    }

    public int indeksKategori(String kategori) {
        for (int i = 0; i < kategoriBuku.length; i++) {
            if (kategoriBuku[i].equalsIgnoreCase(kategori)) return i;
        }
        return -1;
    }

    public Buku cariBuku(String idBuku) {
        if (idBuku == null) return null;
        for (Buku buku : daftarBuku) {
            if (buku.getIdBuku().equalsIgnoreCase(idBuku.trim())) return buku;
        }
        return null;
    }

    public boolean lokasiValid(int lantai, int rak, int posisi) {
        return lantai >= 1 && lantai <= lokasiBuku.length
            && rak >= 1 && rak <= lokasiBuku[0].length
            && posisi >= 1 && posisi <= lokasiBuku[0][0].length;
    }

    public boolean tambahBuku(Buku buku) {
        if (buku == null || buku.getIdBuku() == null || buku.getIdBuku().trim().isEmpty()
                || buku.getJudul() == null || buku.getJudul().trim().isEmpty()
                || cariBuku(buku.getIdBuku()) != null
                || indeksKategori(buku.getKategori()) < 0 || buku.getStok() < 0
                || !lokasiValid(buku.getLantai(), buku.getRak(), buku.getPosisi())) return false;
        int l = buku.getLantai() - 1, r = buku.getRak() - 1, p = buku.getPosisi() - 1;
        if (lokasiBuku[l][r][p] != null) return false;
        daftarBuku.add(buku);
        lokasiBuku[l][r][p] = buku.getIdBuku();
        return true;
    }

    // Hanya boleh dihapus bila tidak ada transaksi peminjaman aktif.
    public boolean hapusBuku(String idBuku, TransaksiController transaksiController) {
        Buku buku = cariBuku(idBuku);
        if (buku == null || transaksiController == null
                || transaksiController.adaPeminjamanAktif(buku.getIdBuku())) return false;
        lokasiBuku[buku.getLantai() - 1][buku.getRak() - 1][buku.getPosisi() - 1] = null;
        return daftarBuku.remove(buku);
    }

    public List<Buku> getDaftarBuku() {
        return Collections.unmodifiableList(daftarBuku);
    }

    public String[][][] getSalinanLokasi() {
        String[][][] salinan = new String[lokasiBuku.length][][];
        for (int l = 0; l < lokasiBuku.length; l++) {
            salinan[l] = new String[lokasiBuku[l].length][];
            for (int r = 0; r < lokasiBuku[l].length; r++) {
                salinan[l][r] = lokasiBuku[l][r].clone();
            }
        }
        return salinan;
    }
}
