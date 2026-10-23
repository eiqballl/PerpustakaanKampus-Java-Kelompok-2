/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.BukuController;
import controller.MahasiswaController;
import controller.TransaksiController;
import java.util.Scanner;
import java.util.Stack;
import model.Buku;
import model.Mahasiswa;
import model.Transaksi;
/**
 *
 * @author Acer
 */
public class PerpustakaanView {
    private final Scanner scanner = new Scanner(System.in);
    private final BukuController bukuController;
    private final MahasiswaController mahasiswaController;
    private final TransaksiController transaksiController;

    public PerpustakaanView(BukuController bukuController,
                            MahasiswaController mahasiswaController,
                            TransaksiController transaksiController) {
        this.bukuController = bukuController;
        this.mahasiswaController = mahasiswaController;
        this.transaksiController = transaksiController;
    }

    private int bacaAngka(String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine().trim();
            try { return Integer.parseInt(input); }
            catch (NumberFormatException e) { System.out.println("Masukkan angka yang valid."); }
        }
    }

    private String bacaTeks(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = scanner.nextLine().trim();
            if (!teks.isEmpty()) return teks;
            System.out.println("Input tidak boleh kosong.");
        }
    }
    
    private void tungguEnter() {
        System.out.println("\nTekan ENTER untuk kembali ke menu utama...");
        scanner.nextLine();
    }

    public void jalankan() {
        int pilihan;
        do {
            System.out.println("\n===== SISTEM PERPUSTAKAAN KAMPUS =====");
            System.out.println("1. Lihat Kategori Buku");
            System.out.println("2. Kelola Data Buku");
            System.out.println("3. Lokasi Penyimpanan Buku");
            System.out.println("4. Antrean Peminjaman");
            System.out.println("5. Proses Peminjaman Buku");
            System.out.println("6. Riwayat dan Pembatalan Transaksi");
            System.out.println("7. Statistik Peminjaman");
            System.out.println("0. Keluar");
            pilihan = bacaAngka("Pilih menu: ");
            switch (pilihan) {
                case 1: tampilKategori(); break;
                case 2: menuBuku(); break;
                case 3: menuLokasi(); break;
                case 4: menuAntrean(); break;
                case 5: prosesPeminjaman(); break;
                case 6: menuTransaksi(); break;
                case 7: menuStatistik(); break;
                case 0: System.out.println("Terima kasih!"); break;
                default: System.out.println("Menu tidak tersedia.");tungguEnter();
                
            }
        } while (pilihan != 0);
    }

    private void tampilKategori() {
        String[] kategori = bukuController.getKategoriBuku();
        System.out.println("\n=== KATEGORI BUKU ===");
        for (int i = 0; i < kategori.length; i++)
            System.out.println((i + 1) + ". " + kategori[i]);
    }

    private void menuBuku() {
        int pilih;
        do {
            System.out.println("\n=== KELOLA BUKU ===");
            System.out.println("1. Tambah Buku\n2. Tampilkan Buku\n3. Cari Buku\n4. Hapus Buku\n0. Kembali");
            pilih = bacaAngka("Pilih: ");
            switch (pilih) {
                case 1: tambahBuku(); break;
                case 2: tampilBuku(); break;
                case 3:
                    Buku buku = bukuController.cariBuku(bacaTeks("ID buku: "));
                    if (buku == null) System.out.println("Buku tidak ditemukan.");
                    else cetakBuku(buku);
                    break;
                case 4:
                    String id = bacaTeks("ID buku yang akan dihapus: ");
                    if (mahasiswaMenungguBuku(id)) {
                        System.out.println("Buku masih ada dalam antrean; tidak bisa dihapus.");
                    } else if (bukuController.hapusBuku(id, transaksiController)) {
                        System.out.println("Buku berhasil dihapus.");
                    } else {
                        System.out.println("Gagal: buku tidak ada atau sedang dipinjam.");
                    }
                    break;
                case 0: break;
                default: System.out.println("Menu tidak tersedia.");
            }
        } while (pilih != 0);
    }

    private boolean mahasiswaMenungguBuku(String idBuku) {
        for (Mahasiswa m : mahasiswaController.getSalinanAntrean()) {
            if (m.getIdBukuDipinjam().equalsIgnoreCase(idBuku)) return true;
        }
        return false;
    }

    private void tambahBuku() {
        String id = bacaTeks("ID buku: ");
        if (bukuController.cariBuku(id) != null) {
            System.out.println("ID buku sudah digunakan."); return;
        }
        String judul = bacaTeks("Judul: ");
        tampilKategori();
        String kategori = bukuController.getKategori(bacaAngka("Nomor kategori: "));
        if (kategori == null) { System.out.println("Kategori tidak valid."); return; }
        int stok = bacaAngka("Stok tersedia: ");
        int lantai = bacaAngka("Lantai (1-2): ");
        int rak = bacaAngka("Rak (1-3): ");
        int posisi = bacaAngka("Posisi (1-5): ");
        Buku buku = new Buku(id, judul, kategori, stok, lantai, rak, posisi);
        System.out.println(bukuController.tambahBuku(buku)
                ? "Buku berhasil ditambahkan." : "Gagal: stok/lokasi tidak valid atau lokasi sudah terisi.");
    }

    private void cetakBuku(Buku b) {
        System.out.printf("ID: %s | Judul: %s | Kategori: %s | Stok: %d | Lantai %d, Rak %d, Posisi %d%n",
                b.getIdBuku(), b.getJudul(), b.getKategori(), b.getStok(),
                b.getLantai(), b.getRak(), b.getPosisi());
    }

    private void tampilBuku() {
        System.out.println("\n=== DAFTAR BUKU ===");
        if (bukuController.getDaftarBuku().isEmpty()) System.out.println("Belum ada buku.");
        for (Buku b : bukuController.getDaftarBuku()) cetakBuku(b);
    }

    private void menuLokasi() {
        int pilih;
        do {
            System.out.println("\n=== LOKASI BUKU ===");
            System.out.println("1. Lihat Semua Lokasi\n2. Cari Lokasi Buku\n0. Kembali");
            pilih = bacaAngka("Pilih: ");
            switch (pilih) {
                case 1:
                    String[][][] lokasi = bukuController.getSalinanLokasi();
                    for (int l = 0; l < lokasi.length; l++) {
                        System.out.println("LANTAI " + (l + 1));
                        for (int r = 0; r < lokasi[l].length; r++) {
                            System.out.println("  RAK " + (r + 1));
                            for (int p = 0; p < lokasi[l][r].length; p++)
                                System.out.println("    Posisi " + (p + 1) + ": "
                                  + (lokasi[l][r][p] == null ? "Kosong" : lokasi[l][r][p]));
                        }
                    }
                    break;
                case 2:
                    Buku buku = bukuController.cariBuku(bacaTeks("ID buku: "));
                    if (buku == null) System.out.println("Buku tidak ditemukan.");
                    else cetakBuku(buku);
                    break;
                case 0: break;
                default: System.out.println("Menu tidak tersedia.");
            }
        } while (pilih != 0);
    }

    private void menuAntrean() {
        int pilih;
        do {
            System.out.println("\n=== ANTREAN PEMINJAMAN ===");
            System.out.println("1. Tambah Antrean\n2. Lihat Semua Antrean\n3. Lihat Terdepan\n0. Kembali");
            pilih = bacaAngka("Pilih: ");
            switch (pilih) {
                case 1:
                    String nim = bacaTeks("NIM: ");
                    String nama = bacaTeks("Nama: ");
                    String id = bacaTeks("ID buku yang ingin dipinjam: ");
                    if (bukuController.cariBuku(id) == null) {
                        System.out.println("Buku tidak terdaftar. Antrean gagal ditambahkan.");
                    } else if (mahasiswaController.tambahAntrean(new Mahasiswa(nim, nama, id))) {
                        System.out.println("Berhasil masuk antrean.");
                    } else System.out.println("Data antrean tidak valid.");
                    break;
                case 2:
                    if (mahasiswaController.antreanKosong()) System.out.println("Antrean kosong.");
                    else {
                        int nomor = 1;
                        for (Mahasiswa m : mahasiswaController.getSalinanAntrean())
                            System.out.println((nomor++) + ". " + m.getNama() + " (" + m.getNim()
                                    + ") - " + m.getIdBukuDipinjam());
                    }
                    break;
                case 3:
                    Mahasiswa m = mahasiswaController.lihatTerdepan();
                    System.out.println(m == null ? "Antrean kosong."
                            : "Terdepan: " + m.getNama() + " - " + m.getIdBukuDipinjam());
                    break;
                case 0: break;
                default: System.out.println("Menu tidak tersedia.");
            }
        } while (pilih != 0);
    }

    private void prosesPeminjaman() {
        Mahasiswa depan = mahasiswaController.lihatTerdepan();
        if (depan == null) { System.out.println("Antrean masih kosong."); return; }
        Buku buku = bukuController.cariBuku(depan.getIdBukuDipinjam());
        if (buku == null || buku.getStok() <= 0) {
            System.out.println("Peminjaman gagal: buku tidak ada atau stok habis. Antrean tetap tersimpan.");
            return;
        }
        Transaksi t = transaksiController.prosesPeminjaman();
        System.out.println(t == null ? "Transaksi gagal."
                : "Peminjaman berhasil! ID transaksi: " + t.getIdTransaksi());
    }

    private void cetakTransaksi(Transaksi t) {
        System.out.println(t.getIdTransaksi() + " | " + t.getTanggalPeminjaman()
                + " | " + t.getNamaMahasiswa() + " (" + t.getNim() + ") | "
                + t.getIdBuku() + " - " + t.getJudulBuku());
    }

    private void menuTransaksi() {
        int pilih;
        do {
            System.out.println("\n=== TRANSAKSI ===");
            System.out.println("1. Lihat Riwayat\n2. Lihat Transaksi Terakhir\n3. Batalkan Transaksi Terakhir\n0. Kembali");
            pilih = bacaAngka("Pilih: ");
            switch (pilih) {
                case 1:
                    Stack<Transaksi> riwayat = transaksiController.getSalinanRiwayat();
                    if (riwayat.isEmpty()) System.out.println("Belum ada transaksi.");
                    while (!riwayat.isEmpty()) cetakTransaksi(riwayat.pop());
                    break;
                case 2:
                    Transaksi terakhir = transaksiController.lihatTerakhir();
                    if (terakhir == null) System.out.println("Riwayat kosong.");
                    else cetakTransaksi(terakhir);
                    break;
                case 3:
                    Transaksi dibatalkan = transaksiController.batalkanTerakhir();
                    System.out.println(dibatalkan == null ? "Tidak ada transaksi yang bisa dibatalkan."
                            : "Berhasil membatalkan " + dibatalkan.getIdTransaksi()
                              + ". Stok dan statistik dikembalikan.");
                    break;
                case 0: break;
                default: System.out.println("Menu tidak tersedia.");
            }
        } while (pilih != 0);
    }

    private void menuStatistik() {
        int pilih;
        do {
            System.out.println("\n=== STATISTIK ===");
            System.out.println("1. Statistik Mingguan\n2. Total per Kategori\n3. Total Keseluruhan\n0. Kembali");
            pilih = bacaAngka("Pilih: ");
            int[][] data = transaksiController.getSalinanStatistik();
            String[] kategori = bukuController.getKategoriBuku();
            switch (pilih) {
                case 1:
                    System.out.printf("%-20s %4s %4s %4s %4s %4s %4s %4s%n",
                            "Kategori", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min");
                    for (int i = 0; i < data.length; i++) {
                        System.out.printf("%-20s", kategori[i]);
                        for (int j = 0; j < data[i].length; j++) System.out.printf(" %4d", data[i][j]);
                        System.out.println();
                    }
                    break;
                case 2:
                    for (int i = 0; i < data.length; i++) {
                        int total = 0;
                        for (int j = 0; j < data[i].length; j++) total += data[i][j];
                        System.out.println(kategori[i] + ": " + total);
                    }
                    break;
                case 3:
                    int total = 0;
                    for (int[] baris : data) for (int jumlah : baris) total += jumlah;
                    System.out.println("Total seluruh peminjaman: " + total);
                    break;
                case 0: break;
                default: System.out.println("Menu tidak tersedia.");
            }
        } while (pilih != 0);
    }
}
