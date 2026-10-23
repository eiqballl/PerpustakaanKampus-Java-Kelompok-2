/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.LinkedList;
import java.util.Queue;
import model.Mahasiswa;
/**
 *
 * @author Acer
 */
public class MahasiswaController {
// Queue FIFO: elemen pertama masuk adalah yang pertama diproses
    private final Queue<Mahasiswa> antreanMahasiswa = new LinkedList<>();

    public boolean tambahAntrean(Mahasiswa mahasiswa) {
        if (mahasiswa == null || mahasiswa.getNim() == null || mahasiswa.getNim().trim().isEmpty()
                || mahasiswa.getNama() == null || mahasiswa.getNama().trim().isEmpty()
                || mahasiswa.getIdBukuDipinjam() == null || mahasiswa.getIdBukuDipinjam().trim().isEmpty()) {
            return false;
        }
        return antreanMahasiswa.offer(mahasiswa);
    }

    public Mahasiswa lihatTerdepan() { return antreanMahasiswa.peek(); }
    public Mahasiswa keluarkanTerdepan() { return antreanMahasiswa.poll(); }
    public boolean antreanKosong() { return antreanMahasiswa.isEmpty(); }
    public int jumlahAntrean() { return antreanMahasiswa.size(); }
    public Queue<Mahasiswa> getSalinanAntrean() { return new LinkedList<>(antreanMahasiswa); }
}

