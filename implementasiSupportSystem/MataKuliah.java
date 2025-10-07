/**
 * Write a description of class Matakuliah here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MataKuliah {
    private String kode;
    private String nama;
    private int sks;
    
    public MataKuliah(String kode, String nama, int sks) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public int getSks() { return sks; }

    @Override
    public String toString() {
        return kode + " - " + nama + " (" + sks + " SKS)";
    }
}
