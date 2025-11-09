public class Penyewa {
    private String nama;
    private Kendaraan kendaraan;

    public Penyewa(String nama, Kendaraan kendaraan) {
        this.nama = nama;
        this.kendaraan = kendaraan;
    }

    public void tampilInfo() {
        System.out.println("Nama Penyewa: " + nama);
        System.out.println("Kendaraan yang disewa:");
        kendaraan.tampilInfo();
    }

    public String getNama() {
        return nama;
    }
}
