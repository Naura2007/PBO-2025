import java.util.*;

public class RentalApp {
    static ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
    static ArrayList<Penyewa> daftarPenyewa = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        isiDataAwal();
        int pilihan;

        do {
            System.out.println("\n===============================");
            System.out.println("SELAMAT DATANG DI RENTAL UNIK");
            System.out.println("===============================");
            System.out.println("1. Lihat Daftar Kendaraan");
            System.out.println("2. Lihat Daftar Penyewa");
            System.out.println("3. Tambah Penyewa Baru");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            pilihan = input.nextInt();
            input.nextLine(); // buang newline

            switch (pilihan) {
                case 1:
                    tampilKendaraan();
                    break;
                case 2:
                    tampilPenyewa();
                    break;
                case 3:
                    tambahPenyewa();
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan Rental Unik!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 4);
    }

    public static void isiDataAwal() {
        daftarKendaraan.add(new Mobil("Toyota", "Avanza", 2020, 4));
        daftarKendaraan.add(new Motor("Honda", "Vario", 2021, 2));
        daftarKendaraan.add(new Sepeda("Polygon", "Helios", 2022, "Balap"));
    }

    public static void tampilKendaraan() {
        System.out.println("\n Daftar Kendaraan Tersedia:");
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            System.out.println((i + 1) + ". " + daftarKendaraan.get(i).getNamaKendaraan());
        }
    }

    public static void tampilPenyewa() {
        if (daftarPenyewa.isEmpty()) {
            System.out.println("\n  Belum ada penyewa yang terdaftar.");
        } else {
            System.out.println("\n Daftar Penyewa:");
            for (Penyewa p : daftarPenyewa) {
                p.tampilInfo();
                System.out.println();
            }
        }
    }

    public static void tambahPenyewa() {
        System.out.print("\nMasukkan nama penyewa: ");
        String nama = input.nextLine();

        tampilKendaraan();
        System.out.print("Pilih kendaraan yang ingin disewa (nomor): ");
        int pilihanKendaraan = input.nextInt();
        input.nextLine();

        if (pilihanKendaraan > 0 && pilihanKendaraan <= daftarKendaraan.size()) {
            Kendaraan k = daftarKendaraan.get(pilihanKendaraan - 1);
            Penyewa penyewaBaru = new Penyewa(nama, k);
            daftarPenyewa.add(penyewaBaru);
            System.out.println(" Penyewa baru berhasil ditambahkan!");
        } else {
            System.out.println(" Pilihan kendaraan tidak valid.");
        }
    }
}
