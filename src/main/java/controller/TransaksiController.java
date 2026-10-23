/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.time.LocalDate;
import java.util.Stack;
import model.Buku;
import model.Mahasiswa;
import model.Transaksi;
/**
 *
 * @author Acer
 */
public class TransaksiController {
    // Stack LIFO: transaksi terbaru ada di atas
    private final Stack<Transaksi> riwayatTransaksi = new Stack<>();
    // Array 2D: baris kategori, kolom Senin(0) sampai Minggu(6)
    private final int[][] statistikPeminjaman = new int[4][7];
    private final BukuController bukuController;
    private final MahasiswaController mahasiswaController;
    private int nomorTransaksi = 1;

    public TransaksiController(BukuController bukuController,
                              MahasiswaController mahasiswaController) {
        this.bukuController = bukuController;
        this.mahasiswaController = mahasiswaController;
    }

    public boolean adaPeminjamanAktif(String idBuku) {
        for (Transaksi transaksi : riwayatTransaksi) {
            if (transaksi.getIdBuku().equalsIgnoreCase(idBuku)) return true;
        }
        return false;
    }

    // Kembalikan null bila peminjaman gagal; antrean tidak dihapus bila gagal.
    public Transaksi prosesPeminjaman() {
        Mahasiswa mahasiswa = mahasiswaController.lihatTerdepan();
        if (mahasiswa == null) return null;
        Buku buku = bukuController.cariBuku(mahasiswa.getIdBukuDipinjam());
        if (buku == null || buku.getStok() <= 0) return null;

        LocalDate tanggal = LocalDate.now();
        int kategori = bukuController.indeksKategori(buku.getKategori());
        int hari = tanggal.getDayOfWeek().getValue() - 1;
        if (kategori < 0) return null;

        Transaksi transaksi = new Transaksi(
            String.format("TR%03d", nomorTransaksi++),
            mahasiswa.getNim(), mahasiswa.getNama(), buku.getIdBuku(),
            buku.getJudul(), buku.getKategori(), tanggal
        );
        buku.setStok(buku.getStok() - 1);
        statistikPeminjaman[kategori][hari]++;
        riwayatTransaksi.push(transaksi);
        mahasiswaController.keluarkanTerdepan();
        return transaksi;
    }

    public Transaksi lihatTerakhir() {
        return riwayatTransaksi.isEmpty() ? null : riwayatTransaksi.peek();
    }

    public Transaksi batalkanTerakhir() {
        if (riwayatTransaksi.isEmpty()) return null;
        Transaksi transaksi = riwayatTransaksi.peek();
        Buku buku = bukuController.cariBuku(transaksi.getIdBuku());
        int kategori = bukuController.indeksKategori(transaksi.getKategori());
        int hari = transaksi.getTanggalPeminjaman().getDayOfWeek().getValue() - 1;
        if (buku == null || kategori < 0 || statistikPeminjaman[kategori][hari] <= 0) return null;
        riwayatTransaksi.pop();
        buku.setStok(buku.getStok() + 1);
        statistikPeminjaman[kategori][hari]--;
        return transaksi;
    }

    public Stack<Transaksi> getSalinanRiwayat() {
        Stack<Transaksi> salinan = new Stack<>();
        salinan.addAll(riwayatTransaksi);
        return salinan;
    }

    public int[][] getSalinanStatistik() {
        int[][] salinan = new int[statistikPeminjaman.length][];
        for (int i = 0; i < statistikPeminjaman.length; i++) {
            salinan[i] = statistikPeminjaman[i].clone();
        }
        return salinan;
    }
}
