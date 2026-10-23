/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main.perpustakaankampus_java_kelompok_2;
import controller.BukuController;
import controller.MahasiswaController;
import controller.TransaksiController;
import view.PerpustakaanView;
/**
 *
 * @author Acer
 */
public class PerpustakaanKampus_Java_Kelompok_2 {

    public static void main(String[] args) {
            
        BukuController bukuController = new BukuController();
        MahasiswaController mahasiswaController = new MahasiswaController();
        TransaksiController transaksiController = new TransaksiController(bukuController, mahasiswaController);
        PerpustakaanView view = new PerpustakaanView( bukuController, mahasiswaController, transaksiController);
        view.jalankan();
    }
    
}
