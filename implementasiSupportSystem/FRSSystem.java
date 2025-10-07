
/**
 * Write a description of class FRSSystem here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
public class FRSSystem
{
    // instance variables - replace the example below with your own
    private List<Mahasiswa> daftarMahasiswa;
    private List<MataKuliah> daftarMatkul;
    private Mahasiswa currentUser;
    private Scanner sc;
    private FRSBot bot;
    /**
     * Constructor for objects of class FRSSystem
     */
    public FRSSystem()
    {
        daftarMahasiswa = new ArrayList<>();
        daftarMatkul = new ArrayList<>();
        sc = new Scanner(System.in);
        bot = new FRSBot();

        // Dummy data
        daftarMahasiswa.add(new Mahasiswa("123", "Budi", "123"));
        daftarMahasiswa.add(new Mahasiswa("456", "Ani", "456"));

        daftarMatkul.add(new MataKuliah("IF101", "Algoritma", 3));
        daftarMatkul.add(new MataKuliah("IF102", "Struktur Data", 3));
        daftarMatkul.add(new MataKuliah("IF201", "Basis Data", 3));
    }
    public void run()
    { 
        while (true) {
            if (currentUser == null) {
                menuLogin();
            } else {
                menuUtama();
            }
        }
    }
    public void menuLogin()
    {
        System.out.println("\n=== LOGIN SISTEM FRS ===");
        System.out.print("NIM     : ");
        String nim = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        for (Mahasiswa m : daftarMahasiswa) {
            if (m.getNim().equals(nim) && m.getPassword().equals(pass)) {
                currentUser = m;
                System.out.println("Login berhasil. Selamat datang, " + m.getNama() + "!");
                return;
            }
        }
        System.out.println("Login gagal. Coba lagi.");
    
    }
    private void menuUtama() 
    {
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("1. Lihat daftar mata kuliah");
        System.out.println("2. Tambah mata kuliah ke FRS");
        System.out.println("3. Hapus mata kuliah dari FRS");
        System.out.println("4. Lihat KRS saya");
        System.out.println("5. Chat dengan bot FRS");
        System.out.println("6. Logout");
        System.out.print("Pilih menu: ");
        String pilih = sc.nextLine();

        switch (pilih) {
            case "1": tampilkanMatkul(); break;
            case "2": tambahFRS(); break;
            case "3": hapusFRS(); break;
            case "4": lihatFRS(); break;
            case "5": menuBot(); break;
            case "6": currentUser = null; break;
            default: System.out.println("Pilihan tidak valid.");
        }
    }
    private void tampilkanMatkul()
    {
        System.out.println("\n--- Daftar Mata Kuliah ---");
        for (MataKuliah mk : daftarMatkul) {
            System.out.println(mk);
        }
    }
    private void tambahFRS()
    {
        tampilkanMatkul();
        System.out.print("Masukkan kode matkul: ");
        String kode = sc.nextLine();

        for (MataKuliah mk : daftarMatkul) {
            if (mk.getKode().equalsIgnoreCase(kode)) {
                currentUser.tambahMatkul(mk);
                System.out.println("|OK| " + mk.getNama() + " ditambahkan ke KRS.");
                return;
            }
        }
        System.out.println("Kode tidak ditemukan.");
    }
    private void hapusFRS() {
        lihatFRS();
        System.out.print("Masukkan kode matkul yang akan dihapus: ");
        String kode = sc.nextLine();
        currentUser.hapusMatkul(kode);
        System.out.println("Jika kode ada, matkul sudah dihapus.");
    }
    private void lihatFRS()
    {
        System.out.println("\n--- KRS " + currentUser.getNama() + " ---");
        if (currentUser.getKRS().isEmpty()) {
            System.out.println("Belum ada mata kuliah yang diambil.");
        } else {
            for (MataKuliah mk : currentUser.getKRS()) {
                System.out.println(mk);
            }
        }
    }
    private void menuBot()
    {
        System.out.println("\n=== Chatbot FRS ===");
        System.out.println("(Ketik 'keluar' untuk kembali ke menu)");
        while (true) {
            System.out.print("Anda: ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("keluar")) break;
            String response = bot.getResponse(input);
            System.out.println("Bot : " + response);
        }
    }
}
